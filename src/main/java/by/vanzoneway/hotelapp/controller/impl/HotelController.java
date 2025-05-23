package by.vanzoneway.hotelapp.controller.impl;

import by.vanzoneway.hotelapp.controller.HotelOperations;
import by.vanzoneway.hotelapp.dto.filter.HotelFilter;
import by.vanzoneway.hotelapp.dto.request.HotelCreateRequestDto;
import by.vanzoneway.hotelapp.dto.response.HotelFullyResponseDto;
import by.vanzoneway.hotelapp.dto.response.HotelShortResponseDto;
import by.vanzoneway.hotelapp.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/property-view")
public class HotelController implements HotelOperations {

    private final HotelService service;

    @GetMapping("/hotels")
    public List<HotelShortResponseDto> getHotels() {
        return this.service.getHotels();
    }

    @GetMapping("/hotels/{id}")
    public HotelFullyResponseDto getHotel(@PathVariable("id") final Long id) {
        return this.service.getHotel(id);
    }

    @GetMapping("/search")
    public List<HotelShortResponseDto> searchHotels(final HotelFilter hotelFilter) {
        return this.service.searchHotels(hotelFilter);
    }

    @PostMapping("/hotels")
    @ResponseStatus(HttpStatus.CREATED)
    public HotelShortResponseDto createHotel(@RequestBody final HotelCreateRequestDto createDto) {
        return this.service.createHotel(createDto);
    }

    @PostMapping("/hotels/{id}/amenities")
    @ResponseStatus(HttpStatus.CREATED)
    public HotelFullyResponseDto addAmenities(
            @PathVariable("id") final Long hotelId,
            @RequestBody final Set<String> amenities) {
        return this.service.addAmenities(hotelId, amenities);
    }

    @GetMapping("/histogram/{param}")
    public Map<String, Long> getHistogram(@PathVariable("param") final String param) {
        return this.service.getHistogram(param);
    }
}
