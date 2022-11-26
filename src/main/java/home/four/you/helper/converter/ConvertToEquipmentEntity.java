package home.four.you.helper.converter;

import java.util.Set;
import java.util.stream.Collectors;

import home.four.you.dto.EquipmentDto;
import home.four.you.model.entity.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import home.four.you.service.EquipmentService;

@Component
public class ConvertToEquipmentEntity implements Converter<EquipmentDto, Equipment> {

	@Autowired
	private EquipmentService EquipmentService;
	
	@Override
	public Equipment convert(EquipmentDto dto) {
		Equipment equipment = null;
		if (dto.getId() != null) {
			EquipmentService.findOne(dto.getId());
		}
		return equipment;
	}
	
	public Set<Equipment> convert(Set<EquipmentDto> dtos) {
		return dtos.stream().map(x -> convert(x)).collect(Collectors.toSet());
	}

}
