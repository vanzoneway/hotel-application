package by.vanzoneway.hotelapp.dto.util;

public record AddressDto(
        Integer houseNumber,
        String street,
        String city,
        String country,
        String postCode) {
}
