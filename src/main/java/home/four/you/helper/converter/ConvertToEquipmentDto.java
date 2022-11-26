package home.four.you.helper.converter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import home.four.you.dto.EquipmentDto;
import home.four.you.model.entity.Equipment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConvertToEquipmentDto implements Converter<Equipment, EquipmentDto> {

	@Override
	public EquipmentDto convert(Equipment e) {
		return new EquipmentDto(e.getId(), e.getName());
	}

	public Set<EquipmentDto> convert(Set<Equipment> equipment) {
		return equipment.stream().map(e -> convert(e)).collect(Collectors.toSet());
	}

	public List<EquipmentDto> convert(List<Equipment> equipment) {
		return equipment.stream().map(e -> convert(e)).collect(Collectors.toList());
	}

}
