package by.vanzoneway.hotelapp.service.impl;

import by.vanzoneway.hotelapp.dto.response.HotelFullyResponseDto;
import by.vanzoneway.hotelapp.dto.response.HotelShortResponseDto;
import by.vanzoneway.hotelapp.exception.hotel.HotelNotFoundException;
import by.vanzoneway.hotelapp.mapper.HotelMapper;
import by.vanzoneway.hotelapp.repository.HotelRepository;
import by.vanzoneway.hotelapp.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static by.vanzoneway.hotelapp.constants.ExceptionMessageKeys.HOTEL_NOT_FOUND_MESSAGE_KEY;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final MessageSource messageSource;

    public List<HotelShortResponseDto> getHotels() {
        return hotelMapper.toHotelShortResponseDtoList(this.hotelRepository.findAll());
    }

    public HotelFullyResponseDto getHotel(Long id) {
        return hotelMapper.toHotelFullyResponseDto(this.hotelRepository
                .findById(id)
                .orElseThrow(() -> new HotelNotFoundException(messageSource.getMessage(
                        HOTEL_NOT_FOUND_MESSAGE_KEY,
                        new Object[]{id},
                        LocaleContextHolder.getLocale()))));
    }
}
