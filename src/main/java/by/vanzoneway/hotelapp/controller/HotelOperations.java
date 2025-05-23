package by.vanzoneway.hotelapp.controller;


import by.vanzoneway.hotelapp.dto.filter.HotelFilter;
import by.vanzoneway.hotelapp.dto.request.HotelCreateRequestDto;
import by.vanzoneway.hotelapp.dto.response.HotelFullyResponseDto;
import by.vanzoneway.hotelapp.dto.response.HotelShortResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Validated
@Tag(name = "hotel-controller", description = "Provides CRUD operations for hotel application")
public interface HotelOperations {

    @GetMapping("/hotels")
    List<HotelShortResponseDto> getHotels();

    @GetMapping("/hotels/{id}")
    HotelFullyResponseDto getHotel(@PathVariable("id") final Long id);

    @GetMapping("/search")
    List<HotelShortResponseDto> searchHotels(final HotelFilter hotelFilter);

    @PostMapping("/hotels")
    @ResponseStatus(HttpStatus.CREATED)
    HotelShortResponseDto createHotel(@Valid @RequestBody HotelCreateRequestDto createDto);

    @PostMapping("/hotels/{id}/amenities")
    @ResponseStatus(HttpStatus.CREATED)
    HotelFullyResponseDto addAmenities(
            @PathVariable final Long id,
            @RequestBody final Set<String> amenities);

    @GetMapping("/histogram/{param}")
    Map<String, Long> getHistogram(@PathVariable final String param);
}