package by.vanzoneway.hotelapp.service.impl;

import by.vanzoneway.hotelapp.dto.filter.HotelFilter;
import by.vanzoneway.hotelapp.dto.request.HotelCreateRequestDto;
import by.vanzoneway.hotelapp.dto.response.HotelFullyResponseDto;
import by.vanzoneway.hotelapp.dto.response.HotelShortResponseDto;
import by.vanzoneway.hotelapp.exception.hotel.HotelNotFoundException;
import by.vanzoneway.hotelapp.exception.hotel.InvalidHistogramParamException;
import by.vanzoneway.hotelapp.mapper.HotelMapper;
import by.vanzoneway.hotelapp.model.entity.Hotel;
import by.vanzoneway.hotelapp.repository.HotelRepository;
import by.vanzoneway.hotelapp.service.HotelService;
import by.vanzoneway.hotelapp.service.util.HistogramParameter;
import by.vanzoneway.hotelapp.specification.HotelSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static by.vanzoneway.hotelapp.constants.ExceptionMessageKeys.HOTEL_NOT_FOUND_MESSAGE_KEY;
import static by.vanzoneway.hotelapp.constants.ExceptionMessageKeys.INVALID_HISTOGRAM_PARAM_MESSAGE_KEY;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final MessageSource messageSource;

    @Override
    @Transactional(readOnly = true)
    public List<HotelShortResponseDto> getHotels() {
        return this.hotelMapper.toHotelShortResponseDtoList(this.hotelRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public HotelFullyResponseDto getHotel(final Long id) {
        return this.hotelMapper.toHotelFullyResponseDto(getHotelOrThrowException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelShortResponseDto> searchHotels(final HotelFilter hotelFilter) {
        Specification<Hotel> spec = HotelSpecifications.applyFilters(hotelFilter);
        return this.hotelMapper.toHotelShortResponseDtoList(this.hotelRepository.findAll(spec));
    }

    @Override
    @Transactional
    public HotelShortResponseDto createHotel(final HotelCreateRequestDto createDto) {
        Hotel hotel = hotelMapper.toHotelWithInformation(createDto);
        Hotel savedHotel = hotelRepository.save(hotel);
        return this.hotelMapper.toHotelShortResponseDto(savedHotel);
    }

    @Override
    @Transactional
    public HotelFullyResponseDto addAmenities(final Long id, final Set<String> amenities) {
        Hotel hotel = getHotelOrThrowException(id);
        hotel.getAmenities().addAll(amenities);
        return this.hotelMapper.toHotelFullyResponseDto(hotel);
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getHistogram(final String param) {
        try {
            HistogramParameter parameter = HistogramParameter.fromStringWithValidation(param);
            return this.convertResults(switch (parameter) {
                case BRAND -> hotelRepository.countByBrand();
                case CITY -> hotelRepository.countByCity();
                case COUNTRY -> hotelRepository.countByCountry();
                case AMENITIES -> hotelRepository.countByAmenities();
            });
        } catch (IllegalArgumentException e) {
            throw new InvalidHistogramParamException(messageSource.getMessage(
                    INVALID_HISTOGRAM_PARAM_MESSAGE_KEY,
                    new Object[]{param},
                    LocaleContextHolder.getLocale()
            ));
        }
    }

    private Map<String, Long> convertResults(final List<Object[]> results) {
        return results.stream()
                .collect(Collectors.toMap(
                        arr -> (String) arr[0],
                        arr -> (Long) arr[1]
                ));
    }

    private Hotel getHotelOrThrowException(final Long id) {
        return this.hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(
                        messageSource.getMessage(
                                HOTEL_NOT_FOUND_MESSAGE_KEY,
                                new Object[]{id},
                                LocaleContextHolder.getLocale())));
    }
}
