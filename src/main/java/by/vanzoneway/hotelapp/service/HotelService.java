package by.vanzoneway.hotelapp.service;

import by.vanzoneway.hotelapp.dto.filter.HotelFilter;
import by.vanzoneway.hotelapp.dto.response.HotelFullyResponseDto;
import by.vanzoneway.hotelapp.dto.response.HotelShortResponseDto;

import java.util.List;

public interface HotelService {

    List<HotelShortResponseDto> getHotels();

    HotelFullyResponseDto getHotel(final Long id);

    List<HotelShortResponseDto> searchHotels(final HotelFilter hotelFilter);
}
