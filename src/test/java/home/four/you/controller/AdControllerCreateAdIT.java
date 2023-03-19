package home.four.you.controller;

import home.four.you.HttpBasedTest;
import home.four.you.exception.ErrorCode;
import home.four.you.model.dto.CreateAdRequestDto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static home.four.you.TestUtil.generateId;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

/**
 * Integration tests for {@link AdController#createAd(CreateAdRequestDto)} endpoint.
 */
@DisplayName("Create ad")
class AdControllerCreateAdIT extends HttpBasedTest {

    @Test
    @DisplayName("Location not found")
    void locationNotFound() throws JSONException {
        var ad = createHouseAdJSON();
        JSONObject property = ad.getJSONObject("property");
        property.put("locationId", generateId());

        given()
                .headers(defaultHeaders())
                .when()
                .contentType(APPLICATION_JSON)
                .body(ad.toString())
                .post(url(ADS_URI))
                .then()
                .statusCode(BAD_REQUEST.value())
                .body("code", equalTo(ErrorCode.BAD_REQUEST.name()))
                .body("message", equalTo("Location not found."));
    }

    @Test
    @DisplayName("House - ok")
    void house_ok() throws JSONException {
        var ad = createHouseAdJSON();
        var property = ad.getJSONObject("property");
        var house = property.getJSONObject("house");
        JSONArray equipment = property.getJSONArray("equipment");

        given()
                .headers(defaultHeaders())
                .when()
                .contentType(APPLICATION_JSON)
                .body(ad.toString())
                .post(url(ADS_URI))
                .then()
                .statusCode(CREATED.value())
                .body("id", notNullValue())
                .body("type", equalTo(ad.getString("type")))
                .body("title", equalTo(ad.getString("title")))
                .body("description", equalTo(ad.getString("description")))
                .body("price", equalTo(ad.getInt("price")))
                .body("createdAt", notNullValue())
                .body("expirationDate", notNullValue())
                .rootPath("property")
                .body("id", notNullValue())
                .body("area", equalTo(property.getInt("area")))
                .body("numberOfRooms", equalTo(Float.valueOf(property.getString("numberOfRooms"))))
                .body("heatType", equalTo(property.getString("heatType")))
                .body("booked", equalTo(property.getBoolean("booked")))
//                .body("equipment", containsInAnyOrder(getEquipment(equipment)))
                .body("location.id", equalTo(property.getInt("locationId")))
                .detachRootPath("property")
                .rootPath("property.house")
                .body("numberOfFloors", equalTo(house.getInt("numberOfFloors")))
                .body("courtyardArea", equalTo(house.getInt("courtyardArea")))
                .detachRootPath("property.house");
    }

    @Test
    @DisplayName("Apartment - ok")
    void apartment_ok() throws JSONException {
        var ad = createApartmentAdJSON();
        var property = ad.getJSONObject("property");
        var apartment = property.getJSONObject("apartment");
        JSONArray equipment = property.getJSONArray("equipment");

        given()
                .headers(defaultHeaders())
                .when()
                .contentType(APPLICATION_JSON)
                .body(ad.toString())
                .post(url(ADS_URI))
                .then()
                .statusCode(CREATED.value())
                .body("id", notNullValue())
                .body("type", equalTo(ad.getString("type")))
                .body("title", equalTo(ad.getString("title")))
                .body("description", equalTo(ad.getString("description")))
                .body("price", equalTo(ad.getInt("price")))
                .body("createdAt", notNullValue())
                .body("expirationDate", notNullValue())
                .rootPath("property")
                .body("id", notNullValue())
                .body("area", equalTo(property.getInt("area")))
                .body("numberOfRooms", equalTo(Float.valueOf(property.getString("numberOfRooms"))))
                .body("heatType", equalTo(property.getString("heatType")))
                .body("booked", equalTo(property.getBoolean("booked")))
//                .body("equipment", equalTo(getEquipment(equipment)))
                .body("location.id", equalTo(property.getInt("locationId")))
                .detachRootPath("property")
                .rootPath("property.apartment")
                .body("floor", equalTo(apartment.getInt("floor")))
                .detachRootPath("property.apartment");
    }

}
