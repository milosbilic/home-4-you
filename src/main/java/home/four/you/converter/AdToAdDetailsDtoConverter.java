package home.four.you.converter;

import home.four.you.mapper.AdMapper;
import home.four.you.model.dto.AdDetailsDto;
import home.four.you.model.entity.Ad;
import org.springframework.core.convert.converter.Converter;

/**
 * Converter from {@link Ad} to {@link AdDetailsDto}.
 */
public class AdToAdDetailsDtoConverter implements Converter<Ad, AdDetailsDto> {

    @Override
    public AdDetailsDto convert(Ad source) {
        return AdMapper.INSTANCE.mapToDetailsDto(source);
    }
}
