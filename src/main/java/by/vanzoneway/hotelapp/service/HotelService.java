package by.vanzoneway.hotelapp.service;

import by.vanzoneway.hotelapp.dto.filter.HotelFilter;
import by.vanzoneway.hotelapp.dto.request.HotelCreateRequestDto;
import by.vanzoneway.hotelapp.dto.response.HotelFullyResponseDto;
import by.vanzoneway.hotelapp.dto.response.HotelShortResponseDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface HotelService {

    List<HotelShortResponseDto> getHotels();

    HotelFullyResponseDto getHotel(final Long id);

    List<HotelShortResponseDto> searchHotels(final HotelFilter hotelFilter);

    HotelShortResponseDto createHotel(final HotelCreateRequestDto createDto);

    HotelFullyResponseDto addAmenities(final Long id, final Set<String> amenities);

    Map<String, Long> getHistogram(final String param);
}
