package by.vanzoneway.hotelapp.constants;

import by.vanzoneway.hotelapp.dto.request.HotelCreateRequestDto;
import by.vanzoneway.hotelapp.dto.response.HotelFullyResponseDto;
import by.vanzoneway.hotelapp.dto.response.HotelShortResponseDto;
import by.vanzoneway.hotelapp.dto.util.AddressDto;
import by.vanzoneway.hotelapp.dto.util.ArrivalTimeDto;
import by.vanzoneway.hotelapp.dto.util.ContactDto;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

@UtilityClass
public class TestDataConstants {

    public static final HotelCreateRequestDto HOTEL_CREATE_REQUEST_DTO = new HotelCreateRequestDto(
            "DoubleTree by Hilton Minsk",
            "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms ...",
            "Hilton",
            new AddressDto(
                    9,
                    "Pobediteley Avenue",
                    "Minsk",
                    "Belarus",
                    "220004"
            ),
            new ContactDto(
                    "+375 17 309-80-00",
                    "doubletreeminsk.info@hilton.com"
            ),
            new ArrivalTimeDto(
                    "14:00",
                    "12:00"
            )
    );

    public static final HotelFullyResponseDto HOTEL_FULLY_RESPONSE_DTO = new HotelFullyResponseDto(
            1L,
            "DoubleTree by Hilton Minsk",
            "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms ...",
            "Hilton",
            new AddressDto(
                    9,
                    "Pobediteley Avenue",
                    "Minsk",
                    "Belarus",
                    "220004"
            ),
            new ContactDto(
                    "+375 17 309-80-00",
                    "doubletreeminsk.info@hilton.com"
            ),
            new ArrivalTimeDto(
                    "14:00",
                    "12:00"
            ),
            new HashSet<>()
    );

    public static final HotelShortResponseDto HOTEL_SHORT_RESPONSE_DTO = new HotelShortResponseDto(
            1L,
            "DoubleTree by Hilton Minsk",
            "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms ...",
            "9 Pobediteley Avenu, Minsk, 220004, Belarus",
            "+375 17 309-80-00"
    );

    public static final List<HotelShortResponseDto> HOTEL_SHORT_RESPONSE_DTO_LIST =
            Stream.generate(() -> HOTEL_SHORT_RESPONSE_DTO)
                    .limit(10)
                    .toList();

    public static final List<Object[]> HISTOGRAM_FROM_DB_EXAMPLE = List.of(
            new Object[]{"Minsk", 2L},
            new Object[]{"Brest", 2L}
    );
}
