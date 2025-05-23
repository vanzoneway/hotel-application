package by.vanzoneway.hotelapp.model.entity;

import by.vanzoneway.hotelapp.model.BaseEntity;
import by.vanzoneway.hotelapp.model.entity.embedded.ArrivalTime;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hotel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "brand")
    private String brand;

    @Embedded
    ArrivalTime arrivalTime;

    @OneToOne(mappedBy = "hotel")
    private HotelContact hotelContact;
}
