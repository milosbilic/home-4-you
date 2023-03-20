package home.four.you.security.auth;

import home.four.you.model.entity.User;
import home.four.you.security.UserOAuth2Principal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static net.bytebuddy.utility.RandomString.make;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link GoogleUserInfo}.
 */
class GoogleUserInfoTest {

    GoogleUserInfo googleUserInfo;

    User user;

    @BeforeEach
    void setUp() {
        user = new User()
                .setEmail(make())
                .setFirstName(make())
                .setLastName(make());
        var attributes = new HashMap<String, Object>();
        attributes.put("sub", user.getEmail());
        attributes.put("given_name", user.getFirstName());
        attributes.put("family_name", user.getLastName());
        attributes.put("email", user.getEmail());

        var oauth2User = new UserOAuth2Principal(user, attributes);

        googleUserInfo = new GoogleUserInfo(oauth2User);
    }

    @Test
    @DisplayName("Get ID")
    void getId() {
        assertThat(googleUserInfo.getId()).isEqualTo(user.getEmail());
    }

    @Test
    @DisplayName("Get first name")
    void getFirstName() {
        assertThat(googleUserInfo.getFirstName()).isEqualTo(user.getFirstName());
    }

    @Test
    @DisplayName("Get last name")
    void getLastName() {
        assertThat(googleUserInfo.getLastName()).isEqualTo(user.getLastName());
    }

    @Test
    @DisplayName("Get email")
    void getEmail() {
        assertThat(googleUserInfo.getEmail()).isEqualTo(user.getEmail());
    }
}
