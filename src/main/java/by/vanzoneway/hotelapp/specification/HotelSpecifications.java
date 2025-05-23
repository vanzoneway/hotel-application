package by.vanzoneway.hotelapp.specification;

import by.vanzoneway.hotelapp.dto.filter.HotelFilter;
import by.vanzoneway.hotelapp.model.entity.Hotel;
import by.vanzoneway.hotelapp.model.entity.HotelInformation;
import by.vanzoneway.hotelapp.model.entity.embedded.Address;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Set;

@UtilityClass
public class HotelSpecifications {

    public Specification<Hotel> applyFilters(final HotelFilter filter) {
        return Specification.allOf(
                byNames(filter.name()),
                byBrands(filter.brand()),
                byCities(filter.city()),
                byCountries(filter.country()),
                byAmenities(filter.amenities())
        );
    }

    private Specification<Hotel> byNames(Set<String> names) {
        return (root, query, cb) ->
                names == null || names.isEmpty() ?
                        cb.conjunction() :
                        cb.or(names.stream()
                                .map(name -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"))
                                .toArray(Predicate[]::new));
    }

    private Specification<Hotel> byBrands(Set<String> brands) {
        return (root, query, cb) ->
                brands == null || brands.isEmpty() ?
                        cb.conjunction() :
                        cb.or(brands.stream()
                                .map(brand -> cb.like(cb.lower(root.get("brand")), "%" + brand.toLowerCase() + "%"))
                                .toArray(Predicate[]::new));
    }

    private Specification<Hotel> byCities(Set<String> cities) {
        return (root, query, cb) -> {
            if (cities == null || cities.isEmpty()) return cb.conjunction();

            Join<Hotel, HotelInformation> infoJoin = root.join("hotelInformation");
            Join<HotelInformation, Address> addressJoin = infoJoin.join("address");
            return cb.or(cities.stream()
                    .map(city -> cb.like(cb.lower(addressJoin.get("city")), "%" + city.toLowerCase() + "%"))
                    .toArray(Predicate[]::new));
        };
    }

    private Specification<Hotel> byCountries(Set<String> countries) {
        return (root, query, cb) -> {
            if (countries == null || countries.isEmpty()) return cb.conjunction();

            Join<Hotel, HotelInformation> infoJoin = root.join("hotelInformation");
            Join<HotelInformation, Address> addressJoin = infoJoin.join("address");
            return cb.or(countries.stream()
                    .map(country -> cb.like(cb.lower(addressJoin.get("country")), "%" + country.toLowerCase() + "%"))
                    .toArray(Predicate[]::new));
        };
    }

    private Specification<Hotel> byAmenities(Set<String> amenities) {
        return (root, query, cb) -> {
            if (amenities == null || amenities.isEmpty()) {
                return cb.conjunction();
            }
            Expression<String> amenitiesExpression = root.join("amenities");
            List<Predicate> predicates = amenities.stream()
                    .map(amenity -> {
                        String searchPattern = "%" + amenity.toLowerCase() + "%";
                        return cb.like(
                                cb.lower(amenitiesExpression),
                                searchPattern
                        );
                    })
                    .toList();
            return cb.or(predicates.toArray(new Predicate[0]));
        };
    }
}
