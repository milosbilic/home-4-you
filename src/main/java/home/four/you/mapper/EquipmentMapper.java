package home.four.you.mapper;

import home.four.you.model.dto.EquipmentDto;
import home.four.you.model.entity.Equipment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for {@link Equipment} entity.
 */
@Mapper(componentModel = "spring")
public interface EquipmentMapper {
    EquipmentMapper INSTANCE = Mappers.getMapper(EquipmentMapper.class);

    /**
     * Maps {@link Equipment} to {@link EquipmentDto}.
     *
     * @param equipment Equipment.
     * @return Mapped equipment.
     */
    EquipmentDto mapToDto(Equipment equipment);
}
