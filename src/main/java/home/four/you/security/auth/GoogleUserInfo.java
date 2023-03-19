package home.four.you.security.auth;

import lombok.Getter;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * Model for Google authenticated user info.
 */
@Getter
public class GoogleUserInfo {

    private final String id;

    private final String firstName;

    private final String lastName;

    private final String email;

    public GoogleUserInfo(OAuth2User oAuth2User) {
        this.id = oAuth2User.getAttribute("sub");
        this.firstName = oAuth2User.getAttribute("given_name");
        this.lastName = oAuth2User.getAttribute("family_name");
        this.email = oAuth2User.getAttribute("email");
    }

}
