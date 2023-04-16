package home.four.you.controller;

import home.four.you.HttpBasedTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static home.four.you.TestUtil.generateId;
import static io.restassured.RestAssured.given;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * Integration tests for {@link AdController#delete(Long)} endpoint.
 */
@DisplayName("Delete Ad")
class AdControllerDeleteIT extends HttpBasedTest {

    @Test
    @DisplayName("Unauthorized")
    void unauthorized() {
        given()
                .headers(defaultHeaders())
                .when()
                .delete(url(AD_URI), generateId())
                .then()
                .statusCode(UNAUTHORIZED.value());
    }

    @Test
    @DisplayName("Forbidden")
    void forbidden() {
        var ad = createRandomAd();

        given()
                .headers(authenticatedHeaders(createRegularUser()))
                .when()
                .delete(url(AD_URI), ad.getId())
                .then()
                .statusCode(FORBIDDEN.value());
    }

    @Test
    @DisplayName("Not found")
    void notFound() {
        var user = createRegularUser();

        given()
                .headers(authenticatedHeaders(user))
                .when()
                .delete(url(AD_URI), generateId())
                .then()
                .statusCode(NOT_FOUND.value());
    }

    @Test
    @DisplayName("Ok, as regular user")
    void ok_asRegularUser() {
        var ad = createRandomAd();

        given()
                .headers(authenticatedHeaders(ad.getOwner()))
                .when()
                .delete(url(AD_URI), ad.getId())
                .then()
                .statusCode(NO_CONTENT.value());
    }

    @Test
    @DisplayName("Ok, as admin")
    void ok_asAdmin() {
        var ad = createRandomAd();

        given()
                .headers(authenticatedHeaders(createAdmin()))
                .when()
                .delete(url(AD_URI), ad.getId())
                .then()
                .statusCode(NO_CONTENT.value());
    }
}
