package advertising.helper.converter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import advertising.dto.EquipmentDto;
import advertising.model.Equipment;

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
