package by.vanzoneway.hotelapp.dto.response;

public record HotelShortResponseDto(
        Long id,
        String name,
        String description,
        String address,
        String phone) {
    
}
