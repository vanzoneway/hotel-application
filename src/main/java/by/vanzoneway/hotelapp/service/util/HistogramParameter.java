package by.vanzoneway.hotelapp.service.util;

import lombok.Getter;

@Getter
public enum HistogramParameter {

    BRAND("brand"),
    CITY("city"),
    COUNTRY("country"),
    AMENITIES("amenities");

    private final String paramName;

    HistogramParameter(String paramName) {
        this.paramName = paramName;
    }

    public static HistogramParameter fromStringWithValidation(String param) {
        for (HistogramParameter value : values()) {
            if (value.paramName.equalsIgnoreCase(param)) {
                return value;
            }
        }
        throw new IllegalArgumentException();
    }
}
