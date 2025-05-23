package by.vanzoneway.hotelapp.dto.filter;

import java.util.Set;

public record HotelFilter(
        Set<String> name,
        Set<String> brand,
        Set<String> city,
        Set<String> country,
        Set<String> amenities
) {

}
