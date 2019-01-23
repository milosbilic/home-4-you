package advertising.helper.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import advertising.dto.HouseDto;
import advertising.model.House;

@Component
public class ConvertToHouseEntity implements Converter<HouseDto, House> {

	@Autowired
	private ConvertToEquipmentEntity toEquipment;
	
	@Override
	public House convert(HouseDto dto) {
		House h = new House();
		h.setArea(dto.getArea());
		h.setBooked(dto.isBooked());
		h.setEquipment(toEquipment.convert(dto.getEquipment()));
		h.setFloorsNumber(dto.getFloorsNumber());
		h.setHeatType(dto.getHeatType());
		h.setImage(dto.getImage());
		
		return null;
	}

}
