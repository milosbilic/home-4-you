package home.four.you.configuration.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * CORS configuration properties.
 */
@ConfigurationProperties(prefix = "home-4-you.cors")
@Getter
@Setter
public class CorsProperties {

    private String[] allowedOrigins;

    private String[] allowedHeaders;

    private String[] allowedMethods;
}
