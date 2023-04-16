package home.four.you.controller;

import home.four.you.HttpBasedTest;
import home.four.you.security.auth.authorization.AuthorityRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static home.four.you.TestUtil.generateId;
import static home.four.you.exception.ErrorCode.ACCESS_DENIED;
import static home.four.you.exception.ErrorMessage.RESOURCE_NOT_FOUND;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * Integration tests for {@link UserController#deleteUser(Long)} endpoint.
 */
@DisplayName("Delete user")
public class UserControllerDeleteUserIT extends HttpBasedTest {

    @Test
    @DisplayName("Unauthenticated")
    void unauthenticated() {
        given()
                .headers(defaultHeaders())
                .when()
                .contentType(APPLICATION_JSON)
                .delete(url(USER_URI), generateId())
                .then()
                .statusCode(UNAUTHORIZED.value())
                .body("message", equalTo(ACCESS_DENIED.name()));
    }

    @Test
    @DisplayName("Forbidden, caller not related")
    void forbidden_callerNotRelated() {
        var user = createRegularUser();

        given()
                .headers(authenticatedHeaders(createRegularUser()))
                .when()
                .contentType(APPLICATION_JSON)
                .delete(url(USER_URI), user.getId())
                .then()
                .statusCode(FORBIDDEN.value());
    }

    @ParameterizedTest
    @DisplayName("Not found")
    @EnumSource(AuthorityRole.class)
    void notFound(AuthorityRole role) {
        var user = createUser(role);

        given()
                .headers(authenticatedHeaders(user))
                .when()
                .contentType(APPLICATION_JSON)
                .delete(url(USER_URI), generateId())
                .then()
                .statusCode(NOT_FOUND.value())
                .body("message", equalTo(RESOURCE_NOT_FOUND));
    }

    @Test
    @DisplayName("Ok, as admin")
    void ok_asAdmin() {
        var user = createRegularUser();

        given()
                .headers(authenticatedHeaders(createAdmin()))
                .when()
                .contentType(APPLICATION_JSON)
                .delete(url(USER_URI), user.getId())
                .then()
                .statusCode(NO_CONTENT.value());
    }

    @Test
    @DisplayName("Ok, as regular user")
    void ok_asRegularUser() {
        var user = createRegularUser();

        given()
                .headers(authenticatedHeaders(user))
                .when()
                .contentType(APPLICATION_JSON)
                .delete(url(USER_URI), user.getId())
                .then()
                .statusCode(NO_CONTENT.value());
    }
}
