package by.vanzoneway.hotelapp.service.impl;

import by.vanzoneway.hotelapp.dto.filter.HotelFilter;
import by.vanzoneway.hotelapp.dto.response.HotelFullyResponseDto;
import by.vanzoneway.hotelapp.dto.response.HotelShortResponseDto;
import by.vanzoneway.hotelapp.exception.hotel.HotelNotFoundException;
import by.vanzoneway.hotelapp.mapper.HotelMapper;
import by.vanzoneway.hotelapp.model.entity.Hotel;
import by.vanzoneway.hotelapp.repository.HotelRepository;
import by.vanzoneway.hotelapp.service.HotelService;
import by.vanzoneway.hotelapp.specification.HotelSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.vanzoneway.hotelapp.constants.ExceptionMessageKeys.HOTEL_NOT_FOUND_MESSAGE_KEY;

@Service
@RequiredArgsConstructor
@Transactional
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final MessageSource messageSource;

    @Override
    @Transactional(readOnly = true)
    public List<HotelShortResponseDto> getHotels() {
        return hotelMapper.toHotelShortResponseDtoList(this.hotelRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public HotelFullyResponseDto getHotel(final Long id) {
        return hotelMapper.toHotelFullyResponseDto(this.hotelRepository
                .findById(id)
                .orElseThrow(() -> new HotelNotFoundException(messageSource.getMessage(
                        HOTEL_NOT_FOUND_MESSAGE_KEY,
                        new Object[]{id},
                        LocaleContextHolder.getLocale()))));
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelShortResponseDto> searchHotels(HotelFilter hotelFilter) {
        Specification<Hotel> spec = HotelSpecifications.applyFilters(hotelFilter);
        return hotelMapper.toHotelShortResponseDtoList(this.hotelRepository.findAll(spec));
    }
}
