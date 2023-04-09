package home.four.you.controller;

import home.four.you.HttpBasedTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static home.four.you.TestUtil.generateId;
import static home.four.you.TestUtil.truncateInstant;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

/**
 * Integration tests for {@link AdController#getDetails(Long)} endpoint.
 */
@DisplayName("Get ad details")
class AdControllerGetDetailsIT extends HttpBasedTest {

    @Test
    @DisplayName("Not found")
    void notFound() {
        given()
                .headers(authenticatedHeaders(createUser()))
                .when()
                .get(url(AD_URI), generateId())
                .then()
                .statusCode(NOT_FOUND.value());
    }

    @Test
    @DisplayName("Ok - unauthenticated")
    void ok_unauthenticated() {
        var ad = createRandomAd();

        given()
                .headers(defaultHeaders())
                .when()
                .get(url(AD_URI), ad.getId())
                .then()
                .statusCode(OK.value())
                .body("id", equalTo(ad.getId().intValue()))
                .body("type", equalTo(ad.getType().toString()))
                .body("title", equalTo(ad.getTitle()))
                .body("description", equalTo(ad.getDescription()))
                .body("price", equalTo(ad.getPrice()))
                .body("createdAt", startsWith(truncateInstant(ad.getCreatedAt())))
                .body("expirationDate", startsWith(truncateInstant(ad.getExpirationDate())));
    }

    @Test
    @DisplayName("Ok - authenticated")
    void ok_authenticated() {
        var ad = createRandomAd();

        given()
                .headers(authenticatedHeaders(createUser()))
                .when()
                .get(url(AD_URI), ad.getId())
                .then()
                .statusCode(OK.value())
                .body("id", equalTo(ad.getId().intValue()))
                .body("type", equalTo(ad.getType().toString()))
                .body("title", equalTo(ad.getTitle()))
                .body("description", equalTo(ad.getDescription()))
                .body("price", equalTo(ad.getPrice()))
                .body("createdAt", startsWith(truncateInstant(ad.getCreatedAt())))
                .body("expirationDate", startsWith(truncateInstant(ad.getExpirationDate())));
    }
}
