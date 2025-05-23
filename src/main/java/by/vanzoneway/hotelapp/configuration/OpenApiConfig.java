package by.vanzoneway.hotelapp.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Hotel Application API",
                description = "Service provides CRUD operations for API", version = "1.0.0",
                contact = @Contact(
                        name = "Ivan Zinovich",
                        email = "vanz.evergarden0@gmail.com"
                )
        )
)
public class OpenApiConfig {

}