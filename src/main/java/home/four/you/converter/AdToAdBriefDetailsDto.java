package home.four.you.converter;

import home.four.you.mapper.AdMapper;
import home.four.you.model.dto.AdBriefDetailsDto;
import home.four.you.model.entity.Ad;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter from {@link Ad} to {@link AdBriefDetailsDto}.
 */
@Component
public class AdToAdBriefDetailsDto implements Converter<Ad, AdBriefDetailsDto> {

    @Override
    public AdBriefDetailsDto convert(Ad ad) {
        return AdMapper.INSTANCE.mapToBriefDetailsDto(ad);
    }
}
