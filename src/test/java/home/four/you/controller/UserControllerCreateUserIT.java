package home.four.you.controller;

import home.four.you.HttpBasedTest;
import home.four.you.model.dto.CreateUserRequestDto;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static home.four.you.exception.ErrorCode.VALIDATION_ERROR;
import static home.four.you.exception.ErrorMessage.INVALID_EMAIL;
import static home.four.you.exception.ErrorMessage.USER_EXISTS;
import static io.restassured.RestAssured.given;
import static net.bytebuddy.utility.RandomString.make;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

/**
 * Integration tests for {@link UserController#createUser(CreateUserRequestDto)}.
 */
@DisplayName("Create user")
public class UserControllerCreateUserIT extends HttpBasedTest {

    @Test
    @DisplayName("Validation errors")
    void validationErrors() {
        var userJSON = new JSONObject();

        given()
                .headers(defaultHeaders())
                .when()
                .contentType(APPLICATION_JSON)
                .body(userJSON.toString())
                .post(url(USERS_URI))
                .then()
                .statusCode(BAD_REQUEST.value())
                .body("message", equalTo(VALIDATION_ERROR.name()))
                .rootPath("details")
                .body("field", containsInAnyOrder(
                        "email", "firstName", "lastName", "phone", "password", "repeatedPassword", "role"));
    }

    @Test
    @DisplayName("Invalid email")
    void invalidEmail() throws JSONException {
        var userJSON = createUserJSON()
                .put("email", make());

        given()
                .headers(defaultHeaders())
                .when()
                .contentType(APPLICATION_JSON)
                .body(userJSON.toString())
                .post(url(USERS_URI))
                .then()
                .statusCode(BAD_REQUEST.value())
                .body("message", equalTo(VALIDATION_ERROR.name()))
                .rootPath("details")
                .body("field", containsInAnyOrder("email"))
                .body("defaultMessage", containsInAnyOrder(INVALID_EMAIL));
    }

    @Test
    @DisplayName("Passwords do not match")
    void passwordsDoNotMatch() throws JSONException {
        var userJSON = createUserJSON()
                .put("repeatedPassword", make());

        given()
                .headers(defaultHeaders())
                .when()
                .contentType(APPLICATION_JSON)
                .body(userJSON.toString())
                .post(url(USERS_URI))
                .then()
                .statusCode(BAD_REQUEST.value())
                .body("message", equalTo(VALIDATION_ERROR.name()))
                .rootPath("details")
                .body("defaultMessage", containsInAnyOrder("Passwords do not match"));
    }

    @Test
    @DisplayName("User exists")
    void userExists() throws JSONException {
        var user = createRegularUser();

        var userJSON = createUserJSON()
                .put("email", user.getEmail());

        given()
                .headers(defaultHeaders())
                .when()
                .contentType(APPLICATION_JSON)
                .body(userJSON.toString())
                .post(url(USERS_URI))
                .then()
                .statusCode(BAD_REQUEST.value())
                .body("message", equalTo(USER_EXISTS));
    }

    @Test
    @DisplayName("Ok, user created")
    void ok_userCreated() throws JSONException {
        var userJSON = createUserJSON();

        given()
                .headers(defaultHeaders())
                .when()
                .contentType(APPLICATION_JSON)
                .body(userJSON.toString())
                .post(url(USERS_URI))
                .then()
                .statusCode(CREATED.value())
                .body("id", notNullValue())
                .body("firstName", equalTo(userJSON.getString("firstName")))
                .body("lastName", equalTo(userJSON.getString("lastName")))
                .body("email", equalTo(userJSON.getString("email")))
                .body("phone", equalTo(userJSON.getString("phone")));
    }
}
