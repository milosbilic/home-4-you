package home.four.you.converter;

import home.four.you.mapper.EquipmentMapper;
import home.four.you.model.dto.EquipmentDto;
import home.four.you.model.entity.Equipment;
import home.four.you.repository.EquipmentRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter from {@link Equipment} to {@link EquipmentDto}.
 */
@Component
public class EquipmentToEquipmentDtoConverter implements Converter<Equipment, EquipmentDto> {

    @Override
    public EquipmentDto convert(Equipment equipment) {
        return EquipmentMapper.INSTANCE.mapToDto(equipment);
    }
}
