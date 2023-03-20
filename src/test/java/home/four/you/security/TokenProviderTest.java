package home.four.you.security;

import home.four.you.configuration.domain.Home4YouProperties;
import home.four.you.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.time.Instant.now;
import static net.bytebuddy.utility.RandomString.make;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link TokenProvider}.
 */
@ExtendWith(MockitoExtension.class)
class TokenProviderTest {

    TokenProvider provider;

    @Mock
    Home4YouProperties home4YouProperties;

    Home4YouProperties.Auth auth;

    User user;

    @BeforeEach
    void setUp() {
        provider = new TokenProvider(home4YouProperties);
        auth = new Home4YouProperties.Auth()
                .setExpirationTime(1999999)
                .setJwtSecret(make());
        user = new User()
                .setFirstName(make())
                .setLastName(make())
                .setEmail(make());
    }

    @Test
    @DisplayName("Create token - ok")
    void createToken_ok() {
        when(home4YouProperties.getAuth()).thenReturn(auth);

        var result = provider.createToken(user);

        Claims claims = Jwts.parser()
                .setSigningKey(home4YouProperties.getAuth().getJwtSecret())
                .parseClaimsJws(result)
                .getBody();

        assertAll(
                () -> assertThat(claims.get("sub", String.class)).isEqualTo(user.getEmail()),
                () -> assertThat(claims.get("firstName", String.class)).isEqualTo(user.getFirstName()),
                () -> assertThat(claims.get("lastName", String.class)).isEqualTo(user.getLastName())
        );
    }

    @Test
    @DisplayName("Validate token - ok, valid")
    void validateToken_okValid() {
        String token = createToken(user);

        when(home4YouProperties.getAuth()).thenReturn(auth);

        boolean result = provider.validateToken(token);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Validate token - invalid")
    void validateToken_invalid() {
        String token = make();

        when(home4YouProperties.getAuth()).thenReturn(auth);

        boolean result = provider.validateToken(token);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Get username from token - ok")
    void getUsernameFromToken_ok() {
        var token = createToken(user);

        when(home4YouProperties.getAuth()).thenReturn(auth);

        String username = provider.getUsernameFromToken(token);

        assertThat(username).isEqualTo(user.getEmail());
    }

    private String createToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());

        return buildToken(user.getEmail(), claims, auth.getJwtSecret());
    }

    private String buildToken(String email, Map<String, Object> claims, String signingKey) {
        return Jwts.builder()
                .setSubject(email)
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(
                        now().plusMillis(auth.getExpirationTime()).toEpochMilli()))
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();
    }
}
