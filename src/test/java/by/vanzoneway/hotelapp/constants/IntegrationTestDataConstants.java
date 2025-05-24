package by.vanzoneway.hotelapp.constants;

import by.vanzoneway.hotelapp.dto.request.HotelCreateRequestDto;
import by.vanzoneway.hotelapp.dto.util.AddressDto;
import by.vanzoneway.hotelapp.dto.util.ArrivalTimeDto;
import by.vanzoneway.hotelapp.dto.util.ContactDto;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.Set;

@UtilityClass
public class IntegrationTestDataConstants {

    public static final int HTTP_STATUS_OK = 200;
    public static final int HTTP_STATUS_CREATED = 201;
    public static final int HTTP_STATUS_BAD_REQUEST = 400;
    public static final int HTTP_STATUS_NOT_FOUND = 404;

    public static final String HOTEL_ENDPOINT = "/property-view/hotels";
    public static final String HOTEL_BY_ID_ENDPOINT = "/property-view/hotels/{id}";
    public static final String HOTEL_AMENITIES_ENDPOINT = "/property-view/hotels/{id}/amenities";
    public static final String HOTEL_HISTOGRAM_ENDPOINT = "/property-view/histogram/{param}";

    public static final Integer HOTEL_ID = 1;
    public static final Long NON_EXISTENT_HOTEL_ID = 999L;
    public static final String HOTEL_NAME = "Grand Plaza";
    public static final String HOTEL_BRAND = "Plaza Hotels";
    public static final String HOTEL_CITY = "New York";
    public static final String HOTEL_EMAIL = "reservations@grandplaza.com";


    public static final Set<String> VALID_AMENITIES = Set.of("Free WiFi", "Swimming Pool");
    public static final Set<String> EXPECTED_AMENITIES = Set.of(
            "Free WiFi", "Swimming Pool", "Spa", "Restaurant", "24/7 Room Service"
    );

    public static final HotelCreateRequestDto VALID_HOTEL_CREATE_REQUEST =
            new HotelCreateRequestDto(
                    "New Test Hotel",
                    "New test hotel description",
                    "Test Brand",
                    new AddressDto(123, "Test Street", "Test City", "Test Country", "12345"),
                    new ContactDto("+1234567890", "test@testhotel.com"),
                    new ArrivalTimeDto("15:00", "11:00")
            );

    public static final HotelCreateRequestDto INVALID_HOTEL_CREATE_REQUEST =
            new HotelCreateRequestDto(
                    "",
                    "Description",
                    "",
                    new AddressDto(0, "", "", "", ""),
                    new ContactDto("", ""),
                    new ArrivalTimeDto("", "")
            );

    public static final Map<String, Long> EXPECTED_BRAND_HISTOGRAM = Map.of(
            "Plaza Hotels", 2L,
            "Oceanview Resorts", 2L,
            "Alpine Hospitality", 2L,
            "Metro Hotels", 2L,
            "Heritage Hotels", 1L,
            "Sunset Hospitality", 1L,
            "Luxury Collection", 1L,
            "Green Resorts", 1L
    );
}