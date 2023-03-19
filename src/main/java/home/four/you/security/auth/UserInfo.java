package home.four.you.security.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

/**
 * Abstract class representing the OAuth user info.
 */
@Getter
@Setter
@ToString
public abstract class UserInfo {

  private final OAuth2AccessToken token;
  private final Map<String, Object> attributes;

  UserInfo(OAuth2UserRequest openAuth2UserRequest, OAuth2User openAuth2User) {
    this.attributes = openAuth2User.getAttributes();
    this.token = openAuth2UserRequest.getAccessToken();
  }

  public abstract String getId();

  public abstract String getFirstName();

  public abstract String getLastName();

  public abstract String getEmail();
}
