package by.vanzoneway.hotelapp.model.entity;

import by.vanzoneway.hotelapp.model.BaseEntity;
import by.vanzoneway.hotelapp.model.entity.embedded.Address;
import by.vanzoneway.hotelapp.model.entity.embedded.Contact;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hotel_information")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelInformation extends BaseEntity {

    @Embedded
    private Contact contacts;

    @Embedded
    private Address address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId
    private Hotel hotel;
}
