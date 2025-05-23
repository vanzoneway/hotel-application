package by.vanzoneway.hotelapp.dto.util;

import jakarta.validation.constraints.NotBlank;

public record ArrivalTimeDto(

        @NotBlank(message = "{empty.checkIn}")
        String checkIn,

        String checkOut) {

}
