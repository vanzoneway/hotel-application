package by.vanzoneway.hotelapp.mapper;

import by.vanzoneway.hotelapp.configuration.GeneralMapperConfig;
import by.vanzoneway.hotelapp.dto.response.HotelFullyResponseDto;
import by.vanzoneway.hotelapp.dto.response.HotelShortResponseDto;
import by.vanzoneway.hotelapp.model.entity.Hotel;
import by.vanzoneway.hotelapp.model.entity.embedded.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(config = GeneralMapperConfig.class)
public interface HotelMapper {

    @Mapping(target = "address", expression = "java(mapAddressToString(hotel.getHotelInformation().getAddress()))")
    @Mapping(target = "phone", source = "hotelInformation.contacts.phone")
    HotelShortResponseDto toHotelShortResponseDto(Hotel hotel);

    @Mapping(target = "address", source = "hotelInformation.address")
    @Mapping(target = "contacts", source = "hotelInformation.contacts")
    HotelFullyResponseDto toHotelFullyResponseDto(Hotel hotel);

    default List<HotelShortResponseDto> toHotelShortResponseDtoList(List<Hotel> hotels) {
        return hotels
                .stream()
                .map(this::toHotelShortResponseDto)
                .toList();
    }

    default String mapAddressToString(Address address) {
        if (address == null) {
            return null;
        }
        return String.format("%s %s, %s, %s, %s",
                address.getHouseNumber().toString(),
                address.getStreet(),
                address.getCity(),
                address.getPostCode(),
                address.getCountry());
    }
}
