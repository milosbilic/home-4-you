package advertising.helper.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import advertising.dto.ApartmentDto;
import advertising.dto.HouseDto;
import advertising.dto.RealEstateDto;
import advertising.model.Apartment;
import advertising.model.House;
import advertising.model.RealEstate;

@Component
public class ConvertToRealEstateDto implements Converter<RealEstate, RealEstateDto>{

	@Autowired
	private ConvertToEquipmentDto toEquipmentDto;
	
	@Override
	public RealEstateDto convert(RealEstate realEstate) {
		RealEstateDto dto = null;
		if (realEstate instanceof House) {
			dto = new HouseDto();
			dto = (HouseDto) setCommonFields(realEstate, dto);
			((HouseDto) dto).setFloorsNumber(((House) realEstate).getFloorsNumber());
		} else if (realEstate instanceof Apartment) {
			dto = new ApartmentDto();
			dto = (ApartmentDto) setCommonFields(realEstate, dto);
			((ApartmentDto) dto).setFloor(((Apartment) realEstate).getFloor());
		}
		return dto;
	}

	private RealEstateDto setCommonFields(RealEstate realEstate, RealEstateDto dto) {
		dto.setArea(realEstate.getArea());
		dto.setBooked(realEstate.isBooked());
		dto.setEquipment(toEquipmentDto.convert(realEstate.getEquipment()));
		dto.setHeatType(realEstate.getHeatType());
		dto.setRoomsNumber(realEstate.getRoomsNumber());
		dto.setId(realEstate.getId());
		dto.setLocation(realEstate.getLocation());
		return dto;
	}
	
}
