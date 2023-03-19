package home.four.you;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Application entry point.
 */
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableConfigurationProperties
public class Home4YouApplication {

    public static void main(String[] args) {
        SpringApplication.run(Home4YouApplication.class, args);
    }
}
