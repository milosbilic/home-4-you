package home.four.you;

import home.four.you.model.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import static home.four.you.TestUtil.generateId;
import static io.restassured.RestAssured.given;
import static net.bytebuddy.utility.RandomString.make;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * Authentication integration tests.
 */
public class AuthenticationIT extends HttpBasedTest {

    @Test
    @DisplayName("Invalid token signature")
    void invalidTokenSignature() {
        var headers = new HttpHeaders();
        headers.setBearerAuth(make());

        given()
                .headers(headers)
                .when()
                .delete(url(AD_URI), generateId())
                .then()
                .statusCode(UNAUTHORIZED.value());
    }

    @Test
    @DisplayName("Username not found")
    void usernameNotFound() {
        var user = new User()
                .setEmail(generateRandomEmail());

        given()
                .headers(authenticatedHeaders(user))
                .when()
                .delete(url(AD_URI), generateId())
                .then()
                .statusCode(UNAUTHORIZED.value());
    }
}
