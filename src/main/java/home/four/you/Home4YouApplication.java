package home.four.you;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Home4YouApplication {

	public static void main(String[] args) {
		SpringApplication.run(Home4YouApplication.class, args);
	}
}
