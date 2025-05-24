package by.vanzoneway.hotelapp.service.impl;

import by.vanzoneway.hotelapp.constants.TestDataConstants;
import by.vanzoneway.hotelapp.dto.filter.HotelFilter;
import by.vanzoneway.hotelapp.dto.request.HotelCreateRequestDto;
import by.vanzoneway.hotelapp.dto.response.HotelFullyResponseDto;
import by.vanzoneway.hotelapp.dto.response.HotelShortResponseDto;
import by.vanzoneway.hotelapp.exception.hotel.HotelNotFoundException;
import by.vanzoneway.hotelapp.exception.hotel.InvalidHistogramParamException;
import by.vanzoneway.hotelapp.mapper.HotelMapper;
import by.vanzoneway.hotelapp.model.entity.Hotel;
import by.vanzoneway.hotelapp.repository.HotelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.jpa.domain.Specification;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HotelServiceImplTest {

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private HotelMapper hotelMapper;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @Test
    void getHotels_ReturnsAllHotels_ValidInputArguments() {
        List<Hotel> hotels = List.of(new Hotel(), new Hotel());
        List<HotelShortResponseDto> expectedResponse = TestDataConstants.HOTEL_SHORT_RESPONSE_DTO_LIST;

        when(hotelRepository.findAll()).thenReturn(hotels);
        when(hotelMapper.toHotelShortResponseDtoList(hotels)).thenReturn(expectedResponse);

        List<HotelShortResponseDto> actual = hotelService.getHotels();

        assertThat(actual).isEqualTo(expectedResponse);
        verify(hotelRepository).findAll();
        verify(hotelMapper).toHotelShortResponseDtoList(hotels);
    }

    @Test
    void getHotel_ReturnsHotel_ValidId() {
        Long hotelId = 1L;
        Hotel hotel = new Hotel();
        HotelFullyResponseDto expectedResponse = TestDataConstants.HOTEL_FULLY_RESPONSE_DTO;

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));
        when(hotelMapper.toHotelFullyResponseDto(hotel)).thenReturn(expectedResponse);

        HotelFullyResponseDto actual = hotelService.getHotel(hotelId);

        assertThat(actual).isEqualTo(expectedResponse);
        verify(hotelRepository).findById(hotelId);
        verify(hotelMapper).toHotelFullyResponseDto(hotel);
    }

    @Test
    void getHotel_ThrowsException_HotelNotFound() {
        Long hotelId = 999L;
        String messageKey = "hotel.not.found";
        Object[] args = new Object[]{hotelId};

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq(messageKey), eq(args), any(Locale.class)))
                .thenReturn("Hotel not found");

        assertThatThrownBy(() -> hotelService.getHotel(hotelId))
                .isInstanceOf(HotelNotFoundException.class)
                .hasMessageContaining("Hotel not found");

        verify(hotelRepository).findById(hotelId);
        verify(hotelMapper, never()).toHotelFullyResponseDto(any());
    }

    @Test
    void searchHotels_ReturnsFilteredHotels_ValidFilter() {
        HotelFilter filter = new HotelFilter(
                Set.of("Hilton"),
                Set.of("Minsk"),
                Set.of("Belarus"),
                Set.of("WiFi"),
                null
        );
        List<Hotel> hotels = List.of(new Hotel());
        List<HotelShortResponseDto> expectedResponse = TestDataConstants.HOTEL_SHORT_RESPONSE_DTO_LIST;

        when(hotelRepository.findAll(Mockito.<Specification<Hotel>>any())).thenReturn(hotels);
        when(hotelMapper.toHotelShortResponseDtoList(hotels)).thenReturn(expectedResponse);

        List<HotelShortResponseDto> actual = hotelService.searchHotels(filter);

        assertThat(actual).isEqualTo(expectedResponse);
        verify(hotelRepository).findAll(Mockito.<Specification<Hotel>>any());
        verify(hotelMapper).toHotelShortResponseDtoList(hotels);
    }

    @Test
    void createHotel_ReturnsCreatedHotel_ValidInput() {
        HotelCreateRequestDto requestDto = TestDataConstants.HOTEL_CREATE_REQUEST_DTO;
        Hotel hotel = new Hotel();
        Hotel savedHotel = new Hotel();
        HotelShortResponseDto expectedResponse = TestDataConstants.HOTEL_SHORT_RESPONSE_DTO;

        when(hotelMapper.toHotelWithInformation(requestDto)).thenReturn(hotel);
        when(hotelRepository.save(hotel)).thenReturn(savedHotel);
        when(hotelMapper.toHotelShortResponseDto(savedHotel)).thenReturn(expectedResponse);

        HotelShortResponseDto actual = hotelService.createHotel(requestDto);

        assertThat(actual).isEqualTo(expectedResponse);
        verify(hotelMapper).toHotelWithInformation(requestDto);
        verify(hotelRepository).save(hotel);
        verify(hotelMapper).toHotelShortResponseDto(savedHotel);
    }

    @Test
    void addAmenities_ReturnsUpdatedHotel_ValidInput() {
        Long hotelId = 1L;
        Set<String> amenities = Set.of("WiFi", "Pool");
        Hotel hotel = new Hotel();
        hotel.setAmenities(new HashSet<>());

        HotelFullyResponseDto expectedResponse = TestDataConstants.HOTEL_FULLY_RESPONSE_DTO;

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));
        when(hotelMapper.toHotelFullyResponseDto(hotel)).thenReturn(expectedResponse);

        HotelFullyResponseDto actual = hotelService.addAmenities(hotelId, amenities);

        assertThat(actual).isEqualTo(expectedResponse);
        assertThat(hotel.getAmenities()).containsAll(amenities);

        verify(hotelRepository).findById(hotelId);
        verify(hotelMapper).toHotelFullyResponseDto(hotel);
    }

    @Test
    void addAmenities_ThrowsException_HotelNotFound() {
        Long hotelId = 999L;
        Set<String> amenities = Set.of("WiFi");
        String messageKey = "hotel.not.found";
        Object[] args = new Object[]{hotelId};

        LocaleContextHolder.setLocale(Locale.US);

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.empty());
        when(messageSource.getMessage(messageKey, args, LocaleContextHolder.getLocale()))
                .thenReturn("Hotel not found");

        assertThatThrownBy(() -> hotelService.addAmenities(hotelId, amenities))
                .isInstanceOf(HotelNotFoundException.class)
                .hasMessageContaining("Hotel not found");

        verify(hotelRepository).findById(hotelId);
        verify(hotelRepository, never()).save(any());

        LocaleContextHolder.resetLocaleContext();
    }

    @Test
    void getHistogram_ReturnsBrandHistogram_ValidParam() {
        String param = "brand";
        List<Object[]> results = TestDataConstants.HISTOGRAM_FROM_DB_EXAMPLE;
        Map<String, Long> expected = Map.of("Minsk", 2L, "Brest", 2L);

        when(hotelRepository.countByBrand()).thenReturn(results);

        Map<String, Long> actual = hotelService.getHistogram(param);

        assertThat(actual).isEqualTo(expected);
        verify(hotelRepository).countByBrand();
    }

    @Test
    void getHistogram_ReturnsCityHistogram_ValidParam() {
        String param = "city";
        List<Object[]> results = TestDataConstants.HISTOGRAM_FROM_DB_EXAMPLE;
        Map<String, Long> expected = Map.of("Minsk", 2L, "Brest", 2L);

        when(hotelRepository.countByCity()).thenReturn(results);

        Map<String, Long> actual = hotelService.getHistogram(param);

        assertThat(actual).isEqualTo(expected);
        verify(hotelRepository).countByCity();
    }

    @Test
    void getHistogram_ThrowsException_InvalidParam() {
        String param = "invalid";
        String messageKey = "invalid.histogram.param";
        Object[] args = new Object[]{param};

        LocaleContextHolder.setLocale(Locale.US);

        when(messageSource.getMessage(messageKey, args, LocaleContextHolder.getLocale()))
                .thenReturn("Invalid histogram parameter");
        
        assertThatThrownBy(() -> hotelService.getHistogram(param))
                .isInstanceOf(InvalidHistogramParamException.class)
                .hasMessageContaining("Invalid histogram parameter");

        LocaleContextHolder.resetLocaleContext();
    }
}