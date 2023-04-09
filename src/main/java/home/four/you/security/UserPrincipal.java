package home.four.you.security;

import home.four.you.security.auth.authorization.AuthorityRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static home.four.you.security.auth.authorization.AuthorityRole.ROLE_ADMIN;


/**
 * Model representing authenticated user principal.
 */

@Getter
@Setter
@Accessors(chain = true)
public class UserPrincipal implements UserDetails {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Collection<SimpleGrantedAuthority> authorities;
    private AuthorityRole role;

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    public boolean isAdmin() {
        return ROLE_ADMIN.equals(role);
    }

}
