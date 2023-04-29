package home.four.you.controller;

import home.four.you.HttpBasedTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import static io.restassured.RestAssured.given;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * Integration tests for {@link UserController#getAllUsers(Pageable)} endpoint.
 */
@DisplayName("Get all users")
public class UserControllerGetAllUsersIT extends HttpBasedTest {

    @Test
    @DisplayName("Unauthorized")
    void unauthorized() {
        given()
                .headers(defaultHeaders())
                .when()
                .contentType(APPLICATION_JSON)
                .get(url(USERS_URI))
                .then()
                .statusCode(UNAUTHORIZED.value());
    }

    @Test
    @DisplayName("Forbidden - regular user")
    void forbidden_regularUser() {
        given()
                .headers(authenticatedHeaders(createRegularUser()))
                .when()
                .contentType(APPLICATION_JSON)
                .get(url(USERS_URI))
                .then()
                .statusCode(FORBIDDEN.value());
    }

    @Test
    @DisplayName("Ok, as admin")
    void ok_asAdmin() {
        given()
                .headers(authenticatedHeaders(createAdmin()))
                .when()
                .contentType(APPLICATION_JSON)
                .get(url(USERS_URI))
                .then()
                .statusCode(OK.value());
    }
}
