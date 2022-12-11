package home.four.you.mapper;

import home.four.you.model.dto.AdBriefDetailsDto;
import home.four.you.model.dto.AdDto;
import home.four.you.model.entity.Ad;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for {@link Ad} entity.
 */
@Mapper(componentModel = "spring")
public interface AdMapper {

    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    /**
     * Maps ad entity to {@link AdDto}.
     *
     * @param ad Ad.
     * @return Mapped ad.
     */
    AdDto mapToDto(Ad ad);

    /**
     * Maps ad entity to {@link AdBriefDetailsDto}.
     *
     * @param ad Ad.
     * @return Mapped ad.
     */
    AdBriefDetailsDto mapToBriefDetailsDto(Ad ad);
}
