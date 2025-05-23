package by.vanzoneway.hotelapp.dto.request;

import by.vanzoneway.hotelapp.dto.util.AddressDto;
import by.vanzoneway.hotelapp.dto.util.ArrivalTimeDto;
import by.vanzoneway.hotelapp.dto.util.ContactDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HotelCreateRequestDto(
        @NotBlank(message = "{empty.name}")
        String name,

        String description,

        @NotBlank(message = "{empty.brand}")
        String brand,

        @NotNull(message = "{empty.address}")
        @Valid
        AddressDto address,

        @NotNull(message = "{empty.contacts}")
        @Valid
        ContactDto contacts,

        @NotNull(message = "{empty.arrivalTime}")
        @Valid
        ArrivalTimeDto arrivalTime) {

}