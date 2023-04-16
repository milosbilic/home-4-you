package home.four.you.controller;

import home.four.you.HttpBasedTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static home.four.you.TestUtil.generateId;
import static home.four.you.exception.ErrorMessage.RESOURCE_NOT_FOUND;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

/**
 * Integration tests for {@link UserControllerDetailsIT} endpoint.
 */
@DisplayName("User details")
public class UserControllerDetailsIT extends HttpBasedTest {

    @Test
    @DisplayName("Not found")
    void notFound() {
        given()
                .headers(defaultHeaders())
                .when()
                .contentType(APPLICATION_JSON)
                .get(url(USER_URI), generateId())
                .then()
                .statusCode(NOT_FOUND.value())
                .body("message", equalTo(RESOURCE_NOT_FOUND));
    }

    @Test
    @DisplayName("Ok, as unauthenticated user")
    void ok_asUnauthenticatedUser() {
        var user = createRegularUser();

        given()
                .headers(defaultHeaders())
                .when()
                .contentType(APPLICATION_JSON)
                .get(url(USER_URI), user.getId())
                .then()
                .statusCode(OK.value())
                .body("id", equalTo(user.getId().intValue()))
                .body("email", equalTo(user.getEmail()))
                .body("firstName", equalTo(user.getFirstName()))
                .body("lastName", equalTo(user.getLastName()))
                .body("phone", equalTo(user.getPhone()));
    }

    @Test
    @DisplayName("Ok, as authenticated user")
    void ok_asAuthenticatedUser() {
        var user = createRegularUser();

        given()
                .headers(authenticatedHeaders(createRegularUser()))
                .when()
                .contentType(APPLICATION_JSON)
                .get(url(USER_URI), user.getId())
                .then()
                .statusCode(OK.value())
                .body("id", equalTo(user.getId().intValue()))
                .body("email", equalTo(user.getEmail()))
                .body("firstName", equalTo(user.getFirstName()))
                .body("lastName", equalTo(user.getLastName()))
                .body("phone", equalTo(user.getPhone()));
    }
}
