package by.vanzoneway.hotelapp.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;

@Validated
@Tag(name = "hotel-controller", description = "Provides CRUD operations for hotel application")
public interface HotelOperations {

    
}
