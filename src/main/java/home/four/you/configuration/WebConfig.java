package home.four.you.config;

import home.four.you.converter.AdToAdBriefDetailsDtoConverter;
import home.four.you.converter.AdToAdDetailsDtoConverter;
import home.four.you.converter.AdToCreateAdResponseDtoConverter;
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
    }
}
