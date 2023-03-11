package home.four.you.mapper;

import home.four.you.model.dto.AdBriefDetailsDto;
import home.four.you.model.dto.AdDetailsDto;
import home.four.you.model.dto.CreateAdResponseDto;
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

    /**
     * Maps ad entity to {@link CreateAdResponseDto}.
     *
     * @param ad Ad.
     * @return Mapped {@link CreateAdResponseDto}.
     */
    CreateAdResponseDto mapToCreateAdResponseDto(Ad ad);

    /**
     * Maps ad entity to full-details DTO.
     *
     * @param ad Ad.
     * @return Mapped {@link AdDetailsDto}.
     */
    @Mapping(source = "owner.id", target = "ownerId")
    AdDetailsDto mapToDetailsDto(Ad ad);
}
