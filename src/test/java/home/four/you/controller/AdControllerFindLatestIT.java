package home.four.you.controller;

import home.four.you.HttpBasedTest;
import home.four.you.model.entity.Ad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import static home.four.you.TestUtil.truncateInstant;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.http.HttpStatus.OK;

/**
 * Integration tests for {@link AdController#(Pageable)};
 */
@DisplayName("Find latest")
public class AdControllerFindLatestIT extends HttpBasedTest {

    @Test
    @DisplayName("Ok - unauthenticated")
    void ok_unauthenticated() {
        Ad ad1 = createRandomAd();
        Ad ad2 = createRandomAd();
        Ad ad3 = createRandomAd();

        given()
                .headers(defaultHeaders())
                .when()
                .get(url(ADS_LATEST_URI))
                .then()
                .statusCode(OK.value())
                .body("size()", is(3))
                .body("id", contains(ad3.getId().intValue(), ad2.getId().intValue(), ad1.getId().intValue()))
                .body("type", contains(ad3.getType().toString(), ad2.getType().toString(), ad1.getType().toString()))
                .body("area", contains(ad3.getProperty().getArea(), ad2.getProperty().getArea(), ad1.getProperty().getArea()))
                .body("numberOfRooms", contains(
                        ad3.getProperty().getNumberOfRooms().floatValue(),
                        ad2.getProperty().getNumberOfRooms().floatValue(),
                        ad1.getProperty().getNumberOfRooms().floatValue()))
                .body("price", contains(ad3.getPrice(), ad2.getPrice(), ad1.getPrice()))
                .body("[0].createdAt", startsWith(truncateInstant(ad3.getCreatedAt())))
                .body("[1].createdAt", startsWith(truncateInstant(ad2.getCreatedAt())))
                .body("[2].createdAt", startsWith(truncateInstant(ad1.getCreatedAt())));
    }

    @Test
    @DisplayName("Ok - authenticated")
    void ok_authenticated() {
        Ad ad1 = createRandomAd();
        Ad ad2 = createRandomAd();
        Ad ad3 = createRandomAd();

        given()
                .headers(authenticatedHeaders(createRegularUser()))
                .when()
                .get(url(ADS_LATEST_URI))
                .then()
                .statusCode(OK.value())
                .body("size()", is(3))
                .body("id", contains(ad3.getId().intValue(), ad2.getId().intValue(), ad1.getId().intValue()))
                .body("type", contains(ad3.getType().toString(), ad2.getType().toString(), ad1.getType().toString()))
                .body("area", contains(ad3.getProperty().getArea(), ad2.getProperty().getArea(), ad1.getProperty().getArea()))
                .body("numberOfRooms", contains(
                        ad3.getProperty().getNumberOfRooms().floatValue(),
                        ad2.getProperty().getNumberOfRooms().floatValue(),
                        ad1.getProperty().getNumberOfRooms().floatValue()))
                .body("price", contains(ad3.getPrice(), ad2.getPrice(), ad1.getPrice()))
                .body("[0].createdAt", startsWith(truncateInstant(ad3.getCreatedAt())))
                .body("[1].createdAt", startsWith(truncateInstant(ad2.getCreatedAt())))
                .body("[2].createdAt", startsWith(truncateInstant(ad1.getCreatedAt())));
    }
}
