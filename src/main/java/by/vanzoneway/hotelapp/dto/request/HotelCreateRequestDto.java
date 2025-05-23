package by.vanzoneway.hotelapp.dto.request;

import by.vanzoneway.hotelapp.dto.util.AddressDto;
import by.vanzoneway.hotelapp.dto.util.ArrivalTimeDto;
import by.vanzoneway.hotelapp.dto.util.ContactDto;

public record HotelCreateRequestDto(
        String name,
        String description,
        String brand,
        AddressDto addressDto,
        ContactDto contactDto,
        ArrivalTimeDto arrivalTimeDto) {

}
