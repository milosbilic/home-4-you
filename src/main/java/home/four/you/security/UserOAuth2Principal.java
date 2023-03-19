package home.four.you.security;

import home.four.you.model.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

import static java.lang.String.valueOf;

/**
 * Model representing authenticated OAuth2 user principal.
 */
@Getter
public class UserOAuth2Principal implements OAuth2User {

    private final Map<String, Object> attributes;
    private final User user;

    public UserOAuth2Principal(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getName() {
        return valueOf(user.getId());
    }
}
