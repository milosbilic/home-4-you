package home.four.you.converter;

import home.four.you.mapper.AdMapper;
import home.four.you.model.dto.AdDto;
import home.four.you.model.dto.ApartmentAdDto;
import home.four.you.model.dto.HouseAdDto;
import home.four.you.model.entity.Ad;
import home.four.you.model.entity.Apartment;
import home.four.you.model.entity.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Converter from {@link Ad} to {@link AdDto}.
 */
@Component
public class AdToAdDtoConverter implements Converter<Ad, AdDto> {

    @Autowired
    private ConvertToRealEstateDto toRealEstateDto;

    @Override
    public AdDto convert(Ad ad) {
        return AdMapper.INSTANCE.mapToDto(ad);
    }
}
