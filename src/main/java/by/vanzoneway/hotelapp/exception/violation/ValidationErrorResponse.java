package by.vanzoneway.hotelapp.exception.violation;

import java.util.List;

public record ValidationErrorResponse (
        List<Violation> violations) {

}
