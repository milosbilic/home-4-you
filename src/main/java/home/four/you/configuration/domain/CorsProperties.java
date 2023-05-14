package home.four.you.configuration.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * CORS configuration properties.
 */
@ConfigurationProperties(prefix = "home-4-you.cors")
@Getter
@Setter
public class CorsProperties {

    private List<String> allowedOrigins;

    private List<String> allowedHeaders;

    private List<String> allowedMethods;
}
