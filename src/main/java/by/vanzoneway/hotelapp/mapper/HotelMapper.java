package by.vanzoneway.hotelapp.mapper;

import by.vanzoneway.hotelapp.configuration.GeneralMapperConfig;
import by.vanzoneway.hotelapp.dto.request.HotelCreateRequestDto;
import by.vanzoneway.hotelapp.dto.response.HotelFullyResponseDto;
import by.vanzoneway.hotelapp.dto.response.HotelShortResponseDto;
import by.vanzoneway.hotelapp.dto.util.AddressDto;
import by.vanzoneway.hotelapp.dto.util.ArrivalTimeDto;
import by.vanzoneway.hotelapp.dto.util.ContactDto;
import by.vanzoneway.hotelapp.model.entity.Hotel;
import by.vanzoneway.hotelapp.model.entity.HotelInformation;
import by.vanzoneway.hotelapp.model.entity.embedded.Address;
import by.vanzoneway.hotelapp.model.entity.embedded.ArrivalTime;
import by.vanzoneway.hotelapp.model.entity.embedded.Contact;
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

    @Mapping(target = "hotelInformation", ignore = true)
    @Mapping(target = "amenities", ignore = true)
    @Mapping(target = "id", ignore = true)
    Hotel toHotel(HotelCreateRequestDto createDto);

    Address toAddress(AddressDto addressDto);

    Contact toContact(ContactDto contactDto);

    ArrivalTime toArrivalTime(ArrivalTimeDto arrivalTimeDto);

    default Hotel toHotelWithInformation(HotelCreateRequestDto createDto) {
        Hotel hotel = toHotel(createDto);

        HotelInformation information = new HotelInformation();
        information.setAddress(this.toAddress(createDto.address()));
        Contact contact = this.toContact(createDto.contacts());
        information.setContacts(contact);
        information.setHotel(hotel);

        hotel.setHotelInformation(information);
        hotel.setArrivalTime(this.toArrivalTime(createDto.arrivalTime()));
        return hotel;
    }

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
