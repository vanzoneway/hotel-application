package by.vanzoneway.hotelapp.repository;

import by.vanzoneway.hotelapp.model.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
