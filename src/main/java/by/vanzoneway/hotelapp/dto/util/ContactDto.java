package by.vanzoneway.hotelapp.dto.util;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ContactDto(

        @NotBlank(message = "{empty.phone}")
        String phone,

        @NotBlank(message = "{empty.email}")
        @Email(message = "{invalid.format.email}")
        String email
) {

}