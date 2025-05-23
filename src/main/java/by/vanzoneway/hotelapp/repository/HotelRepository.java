package by.vanzoneway.hotelapp.repository;

import by.vanzoneway.hotelapp.model.entity.Hotel;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepositoryImplementation<Hotel, Long> {

}
