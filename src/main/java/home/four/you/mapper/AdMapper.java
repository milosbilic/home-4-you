package home.four.you.mapper;

import home.four.you.model.dto.AdBriefDetailsDto;
import home.four.you.model.dto.AdDto;
import home.four.you.model.entity.Ad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for {@link Ad} entity.
 */
@Mapper(componentModel = "spring")
public interface AdMapper {

    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    /**
     * Maps ad entity to {@link AdBriefDetailsDto}.
     *
     * @param ad Ad.
     * @return Mapped ad.
     */
    @Mapping(source = "property.area", target = "area")
    @Mapping(source = "property.numberOfRooms", target = "numberOfRooms")
    AdBriefDetailsDto mapToBriefDetailsDto(Ad ad);
}
