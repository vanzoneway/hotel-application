package by.vanzoneway.hotelapp.controller.impl;

import by.vanzoneway.hotelapp.controller.HotelOperations;
import by.vanzoneway.hotelapp.dto.filter.HotelFilter;
import by.vanzoneway.hotelapp.dto.response.HotelFullyResponseDto;
import by.vanzoneway.hotelapp.dto.response.HotelShortResponseDto;
import by.vanzoneway.hotelapp.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/property-view")
public class HotelController implements HotelOperations {

    private final HotelService hotelService;

    @GetMapping("/hotels")
    public List<HotelShortResponseDto> getHotels() {
        return this.hotelService.getHotels();
    }

    @GetMapping("/hotels/{id}")
    public HotelFullyResponseDto getHotel(@PathVariable("id") final Long id) {
        return this.hotelService.getHotel(id);
    }

    @GetMapping("/search")
    public List<HotelShortResponseDto> searchHotels(final HotelFilter hotelFilter) {
        return this.hotelService.searchHotels(hotelFilter);
    }
}
