package home.four.you.security;

import home.four.you.security.auth.authorization.AuthorityRole;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static home.four.you.security.auth.authorization.AuthorityRole.ROLE_ADMIN;


/**
 * Model representing authenticated user principal.
 */
@Builder
@Getter
public class UserPrincipal implements UserDetails {

    private final Long id;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final Collection<SimpleGrantedAuthority> authorities;
    private final AuthorityRole role;

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
