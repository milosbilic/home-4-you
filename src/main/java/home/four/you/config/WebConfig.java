package home.four.you.config;

import home.four.you.converter.AdToAdBriefDetailsDtoConverter;
import home.four.you.converter.AdToAdDtoConverter;
import home.four.you.converter.EquipmentToEquipmentDtoConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new AdToAdBriefDetailsDtoConverter());
        registry.addConverter(new AdToAdDtoConverter());
        registry.addConverter(new EquipmentToEquipmentDtoConverter());
    }
}
