package home.four.you.controller;

import home.four.you.HttpBasedTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static home.four.you.TestUtil.generateId;
import static io.restassured.RestAssured.given;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * Integration tests for {@link AdController#delete(Long)} endpoint.
 */
@DisplayName("Delete Ad")
public class AdControllerDeleteIT extends HttpBasedTest {

    @Test
    @DisplayName("Not found")
    void notFound() {
        given()
                .headers(defaultHeaders())
                .when()
                .delete(url(AD_URI), generateId())
                .then()
                .statusCode(NOT_FOUND.value());
    }

    @Test
    @DisplayName("Ok")
    void ok() {
        var ad = createRandomAd();

        given()
                .headers(defaultHeaders())
                .when()
                .delete(url(AD_URI), ad.getId())
                .then()
                .statusCode(NO_CONTENT.value());
    }
}
