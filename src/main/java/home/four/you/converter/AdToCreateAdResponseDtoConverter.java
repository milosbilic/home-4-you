package home.four.you.converter;

import home.four.you.mapper.AdMapper;
import home.four.you.model.dto.CreateAdResponseDto;
import home.four.you.model.entity.Ad;
import org.springframework.core.convert.converter.Converter;

/**
 * Converter from {@link Ad} to {@link CreateAdResponseDto}.
 */
public class AdToCreateAdResponseDtoConverter implements Converter<Ad, CreateAdResponseDto> {

    @Override
    public CreateAdResponseDto convert(Ad source) {
        return AdMapper.INSTANCE.mapToCreateAdResponseDto(source);
    }
}
