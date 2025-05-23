package by.vanzoneway.hotelapp.dto.enums;

public enum HistogramParameter {
    BRAND("brand"),
    CITY("city"),
    COUNTRY("country"),
    AMENITIES("amenities");

    private final String paramName;

    HistogramParameter(String paramName) {
        this.paramName = paramName;
    }

    public String getParamName() {
        return paramName;
    }

    public static HistogramParameter fromString(String param) {
        for (HistogramParameter value : values()) {
            if (value.paramName.equalsIgnoreCase(param)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unsupported parameter: " + param);
    }
}
