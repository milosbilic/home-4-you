package home.four.you.configuration;

import home.four.you.converter.AdToAdBriefDetailsDtoConverter;
import home.four.you.converter.AdToAdDetailsDtoConverter;
import home.four.you.converter.AdToCreateAdResponseDtoConverter;
import home.four.you.converter.UserToCreatedUserResponseDtoConverter;
import home.four.you.converter.UserToUserDetailsDtoConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class used for web-related beans.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new AdToAdBriefDetailsDtoConverter());
        registry.addConverter(new AdToAdDetailsDtoConverter());
        registry.addConverter(new AdToCreateAdResponseDtoConverter());
        registry.addConverter(new UserToCreatedUserResponseDtoConverter());
        registry.addConverter(new UserToUserDetailsDtoConverter());
    }
}
