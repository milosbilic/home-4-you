package home.four.you.controller;

import home.four.you.HttpBasedTest;
import home.four.you.model.PropertyType;
import home.four.you.model.entity.Ad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import static io.restassured.RestAssured.given;
import static org.springframework.http.HttpStatus.OK;

/**
 * Integration tests for {@link AdController
 * #search(Ad.Type, Integer, Integer, PropertyType, Integer, Integer, Integer, Integer, Pageable)} (Pageable)}.
 */
@DisplayName("Search")
class AdControllerSearchIT extends HttpBasedTest {

    @Test
    @DisplayName("Ok - unauthenticated")
    void ok_unauthenticated() {
        given()
                .headers(defaultHeaders())
                .when()
                .get(url(ADS_SEARCH_URI))
                .then()
                .statusCode(OK.value());
    }

    @Test
    @DisplayName("Ok - authenticated")
    void ok_authenticated() {
        given()
                .headers(authenticatedHeaders(createRegularUser()))
                .when()
                .get(url(ADS_SEARCH_URI))
                .then()
                .statusCode(OK.value());
    }
}
