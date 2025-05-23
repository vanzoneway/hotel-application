package by.vanzoneway.hotelapp.dto.response;

import by.vanzoneway.hotelapp.dto.util.AddressDto;
import by.vanzoneway.hotelapp.dto.util.ArrivalTimeDto;
import by.vanzoneway.hotelapp.dto.util.ContactDto;

import java.util.Set;

public record HotelFullyResponseDto(
        Long id,
        String name,
        String description,
        String brand,
        AddressDto address,
        ContactDto contacts,
        ArrivalTimeDto arrivalTime,
        Set<String> amenities) {

}
