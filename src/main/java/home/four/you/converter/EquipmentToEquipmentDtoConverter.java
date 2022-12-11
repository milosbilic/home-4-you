package home.four.you.converter;

import home.four.you.mapper.EquipmentMapper;
import home.four.you.model.dto.EquipmentDto;
import home.four.you.model.entity.Equipment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Converter from {@link Equipment} to {@link EquipmentDto}.
 */
@Component
public class EquipmentToEquipmentDtoConverter implements Converter<Equipment, EquipmentDto> {

    @Override
    public EquipmentDto convert(Equipment e) {
        return EquipmentMapper.INSTANCE.mapToDto(e);
    }

}
