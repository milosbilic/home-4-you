package home.four.you.converter;

import home.four.you.model.dto.AdDto;
import home.four.you.model.entity.Ad;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter from {@link Ad} to {@link AdDto}.
 */
@Component
public class AdToAdDtoConverter implements Converter<Ad, AdDto> {

    @Override
    public AdDto convert(Ad ad) {
        return null;
    }
}
