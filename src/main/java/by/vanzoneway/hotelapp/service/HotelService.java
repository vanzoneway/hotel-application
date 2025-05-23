package by.vanzoneway.hotelapp.service;

import by.vanzoneway.hotelapp.dto.response.HotelFullyResponseDto;
import by.vanzoneway.hotelapp.dto.response.HotelShortResponseDto;

import java.util.List;

public interface HotelService {

    List<HotelShortResponseDto> getHotels();

    HotelFullyResponseDto getHotel(Long id);
}
