package home.four.you.configuration.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Application properties used for configuring the API.
 */
@ConfigurationProperties(prefix = "home-4-you")
@Getter
@Setter
public class Home4YouProperties {

    private final Auth auth = new Auth();

    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Auth {

        private String jwtSecret;

        private long expirationTime;
    }

}
