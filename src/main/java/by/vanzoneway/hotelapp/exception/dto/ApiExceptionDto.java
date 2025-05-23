package by.vanzoneway.hotelapp.exception.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiExceptionDto(

        HttpStatus status,

        String message,

        LocalDateTime timestamp) {

}
