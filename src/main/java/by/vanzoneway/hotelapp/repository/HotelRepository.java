package by.vanzoneway.hotelapp.repository;

import by.vanzoneway.hotelapp.model.entity.Hotel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepositoryImplementation<Hotel, Long> {

    @Query("SELECT h.brand, COUNT(h) FROM Hotel h GROUP BY h.brand")
    List<Object[]> countByBrand();

    @Query("SELECT hi.address.city, COUNT(h) FROM Hotel h JOIN h.hotelInformation hi GROUP BY hi.address.city")
    List<Object[]> countByCity();

    @Query("SELECT hi.address.country, COUNT(h) FROM Hotel h JOIN h.hotelInformation hi GROUP BY hi.address.country")
    List<Object[]> countByCountry();

    @Query("SELECT a, COUNT(DISTINCT h.id) FROM Hotel h JOIN h.amenities a GROUP BY a")
    List<Object[]> countByAmenities();
}
