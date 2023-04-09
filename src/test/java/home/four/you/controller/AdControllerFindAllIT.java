package home.four.you.controller;

import home.four.you.HttpBasedTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import static io.restassured.RestAssured.given;
import static org.springframework.http.HttpStatus.OK;

/**
 * Integration tests for {@link AdController#findAll(Pageable)}.
 */
@DisplayName("Find all ads")
class AdControllerFindAllIT extends HttpBasedTest {

    @Test
    @DisplayName("Ok - unauthenticated")
    void ok_unauthenticated() {
        given()
                .headers(defaultHeaders())
                .when()
                .get(url(ADS_URI))
                .then()
                .statusCode(OK.value());
    }

    @Test
    @DisplayName("Ok - authenticated")
    void ok_authenticated() {
        given()
                .headers(authenticatedHeaders(createRegularUser()))
                .when()
                .get(url(ADS_URI))
                .then()
                .statusCode(OK.value());
    }
}
