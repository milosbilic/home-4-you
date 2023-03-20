package home.four.you.security;

import home.four.you.configuration.domain.Home4YouProperties;
import home.four.you.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

/**
 * JWT related operations related class.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final Home4YouProperties home4YouProperties;

    /**
     * Generates JWT token for user with given details.
     *
     * @param user User for whom the token is generated.
     * @return Generated JWT token
     */
    public String createToken(User user) {
        log.debug("Creating JWT for user {}", user.getEmail());

        var claims = new HashMap<String, Object>();
        claims.put("sub", user.getEmail());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());

        Home4YouProperties.Auth auth = home4YouProperties.getAuth();
        String secretKey = auth.getJwtSecret();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + auth.getExpirationTime()))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    /**
     * Validates JWT token. Token is valid if it's parsed correctly.
     *
     * @param authToken Authorization token.
     * @return true if validated or false.
     */
    public boolean validateToken(String authToken) {
        log.debug("Validating JWT token {}", authToken);

        return validateTokenSignedWith(authToken, home4YouProperties.getAuth().getJwtSecret());
    }

    /**
     * Gets username from token.
     *
     * @param token Token.
     * @return Username.
     */
    public String getUsernameFromToken(String token) {
        log.debug("Getting user name from token {}", token);

        return extractSubjectFromToken(token, home4YouProperties.getAuth().getJwtSecret());
    }

    private Claims getJwsClaims(String authToken, String signingKey) {
        return Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(authToken)
                .getBody();
    }

    private boolean validateTokenSignedWith(String authToken, String signingKey) {
        try {
            getJwsClaims(authToken, signingKey);
        } catch (Exception ex) {
            return false;
        }

        return true;
    }

    private String extractSubjectFromToken(String token, String signingKey) {
        return getJwsClaims(token, signingKey)
                .getSubject();
    }
}
