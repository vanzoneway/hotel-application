package by.vanzoneway.hotelapp.controller.impl;

import by.vanzoneway.hotelapp.dto.request.HotelCreateRequestDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import static by.vanzoneway.hotelapp.constants.IntegrationTestDataConstants.EXPECTED_AMENITIES;
import static by.vanzoneway.hotelapp.constants.IntegrationTestDataConstants.HOTEL_AMENITIES_ENDPOINT;
import static by.vanzoneway.hotelapp.constants.IntegrationTestDataConstants.HOTEL_BRAND;
import static by.vanzoneway.hotelapp.constants.IntegrationTestDataConstants.HOTEL_BY_ID_ENDPOINT;
import static by.vanzoneway.hotelapp.constants.IntegrationTestDataConstants.HOTEL_CITY;
import static by.vanzoneway.hotelapp.constants.IntegrationTestDataConstants.HOTEL_EMAIL;
import static by.vanzoneway.hotelapp.constants.IntegrationTestDataConstants.HOTEL_ENDPOINT;
import static by.vanzoneway.hotelapp.constants.IntegrationTestDataConstants.HOTEL_HISTOGRAM_ENDPOINT;
import static by.vanzoneway.hotelapp.constants.IntegrationTestDataConstants.HOTEL_ID;
import static by.vanzoneway.hotelapp.constants.IntegrationTestDataConstants.HOTEL_NAME;
import static by.vanzoneway.hotelapp.constants.IntegrationTestDataConstants.HTTP_STATUS_BAD_REQUEST;
import static by.vanzoneway.hotelapp.constants.IntegrationTestDataConstants.HTTP_STATUS_CREATED;
import static by.vanzoneway.hotelapp.constants.IntegrationTestDataConstants.HTTP_STATUS_NOT_FOUND;
import static by.vanzoneway.hotelapp.constants.IntegrationTestDataConstants.HTTP_STATUS_OK;
import static by.vanzoneway.hotelapp.constants.IntegrationTestDataConstants.INVALID_HOTEL_CREATE_REQUEST;
import static by.vanzoneway.hotelapp.constants.IntegrationTestDataConstants.NON_EXISTENT_HOTEL_ID;
import static by.vanzoneway.hotelapp.constants.IntegrationTestDataConstants.VALID_AMENITIES;
import static by.vanzoneway.hotelapp.constants.IntegrationTestDataConstants.VALID_HOTEL_CREATE_REQUEST;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class HotelControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = this.port;
    }

    @Test
    void getHotels_ReturnsListOfHotels_WhenHotelsExist() {
        given()
                .when()
                .get(HOTEL_ENDPOINT)
                .then()
                .statusCode(HTTP_STATUS_OK)
                .contentType(ContentType.JSON)
                .body("size()", equalTo(12))
                .body("[0].id", equalTo(HOTEL_ID))
                .body("[0].name", equalTo(HOTEL_NAME));
    }

    @Test
    void getHotel_ReturnsHotel_WhenHotelExists() {
        given()
                .when()
                .get(HOTEL_BY_ID_ENDPOINT, HOTEL_ID)
                .then()
                .statusCode(HTTP_STATUS_OK)
                .contentType(ContentType.JSON)
                .body("id", equalTo(HOTEL_ID))
                .body("name", equalTo(HOTEL_NAME))
                .body("brand", equalTo(HOTEL_BRAND))
                .body("address.city", equalTo(HOTEL_CITY))
                .body("contacts.email", equalTo(HOTEL_EMAIL));
    }

    @Test
    void createHotel_ReturnsCreatedHotel_WhenDataIsValid() {
        HotelCreateRequestDto request = VALID_HOTEL_CREATE_REQUEST;

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(HOTEL_ENDPOINT)
                .then()
                .statusCode(HTTP_STATUS_CREATED)
                .contentType(ContentType.JSON)
                .body("name", equalTo(request.name()));
    }

    @Test
    void addAmenities_ReturnsUpdatedHotel_WhenAmenitiesAreValid() {
        given()
                .contentType(ContentType.JSON)
                .body(VALID_AMENITIES)
                .when()
                .post(HOTEL_AMENITIES_ENDPOINT, HOTEL_ID)
                .then()
                .statusCode(HTTP_STATUS_CREATED)
                .contentType(ContentType.JSON)
                .body("amenities",
                        containsInAnyOrder(EXPECTED_AMENITIES.toArray()));
    }

    @Test
    void getHistogram_ReturnsHistogramData_WhenParamIsValid() {
        given()
                .when()
                .get(HOTEL_HISTOGRAM_ENDPOINT, "brand")
                .then()
                .statusCode(HTTP_STATUS_OK)
                .contentType(ContentType.JSON)
                .body("\"Green Resorts\"", equalTo(1));
    }

    @Test
    void getHotel_ReturnsNotFound_WhenHotelDoesNotExist() {
        given()
                .when()
                .get(HOTEL_BY_ID_ENDPOINT, NON_EXISTENT_HOTEL_ID)
                .then()
                .statusCode(HTTP_STATUS_NOT_FOUND);
    }

    @Test
    void createHotel_ReturnsBadRequest_WhenDataIsInvalid() {
        given()
                .contentType(ContentType.JSON)
                .body(INVALID_HOTEL_CREATE_REQUEST)
                .when()
                .post(HOTEL_ENDPOINT)
                .then()
                .statusCode(HTTP_STATUS_BAD_REQUEST);
    }

    @Test
    void addAmenities_ReturnsNotFound_WhenHotelDoesNotExist() {
        given()
                .contentType(ContentType.JSON)
                .body(VALID_AMENITIES)
                .when()
                .post(HOTEL_AMENITIES_ENDPOINT, NON_EXISTENT_HOTEL_ID)
                .then()
                .statusCode(HTTP_STATUS_NOT_FOUND);
    }

    @Test
    void getHistogram_ReturnsBadRequest_WhenParamIsInvalid() {
        given()
                .when()
                .get(HOTEL_HISTOGRAM_ENDPOINT, "invalid")
                .then()
                .statusCode(HTTP_STATUS_BAD_REQUEST);
    }
}