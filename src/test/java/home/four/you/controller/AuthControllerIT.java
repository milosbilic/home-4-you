package home.four.you.controller;

import home.four.you.HttpBasedTest;
import home.four.you.repository.UserRepository;
import home.four.you.security.auth.authorization.AuthorityRole;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.given;
import static net.bytebuddy.utility.RandomString.make;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;

/**
 * Integration tests for {@link AuthController}.
 */
public class AuthControllerIT extends HttpBasedTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Login - user not found")
    void login_userNotFound() throws JSONException {
        given()
                .headers(defaultHeaders())
                .when()
                .contentType(APPLICATION_JSON)
                .body(createLoginRequestJSON().toString())
                .post(url(LOGIN_URI))
                .then()
                .statusCode(FORBIDDEN.value());
    }

    @Test
    @DisplayName("Login - password does not match")
    void login_passwordDoesNotMatch() throws JSONException {
        var user = createUser(AuthorityRole.ROLE_ADMIN);
        JSONObject loginRequestJSON = createLoginRequestJSON(user.getEmail(), user.getPassword());

        given()
                .headers(defaultHeaders())
                .when()
                .contentType(APPLICATION_JSON)
                .body(loginRequestJSON.toString())
                .post(url(LOGIN_URI))
                .then()
                .statusCode(FORBIDDEN.value());
    }

    @Test
    @DisplayName("Login - ok")
    void login_ok() throws JSONException {
        var email = generateRandomEmail();
        String password = make();

        given()
                .headers(defaultHeaders())
                .when()
                .contentType(APPLICATION_JSON)
                .body(createUserJSON(email, password).toString())
                .post(url(USERS_URI))
                .then()
                .statusCode(CREATED.value());

        given()
                .headers(defaultHeaders())
                .when()
                .contentType(APPLICATION_JSON)
                .body(createLoginRequestJSON(email, password).toString())
                .post(url(LOGIN_URI))
                .then()
                .statusCode(OK.value());
    }
}
