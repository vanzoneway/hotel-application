package by.vanzoneway.hotelapp.dto.util;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AddressDto(
        @NotNull(message = "{empty.houseNumber}")
        @Positive(message = "{negative.houseNumber}")
        Integer houseNumber,

        @NotBlank(message = "{empty.street}")
        String street,

        @NotBlank(message = "{empty.city}")
        String city,

        @NotBlank(message = "{empty.country}")
        String country,

        @NotBlank(message = "{empty.post}")
        String postCode) {

}