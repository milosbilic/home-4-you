package home.four.you.converter;

import home.four.you.dto.ApartmentDto;
import home.four.you.dto.HouseDto;
import home.four.you.model.entity.Apartment;
import home.four.you.model.entity.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import home.four.you.dto.RealEstateDto;
import home.four.you.model.entity.House;

@Component
public class ConvertToRealEstateDto implements Converter<Property, RealEstateDto>{

	@Autowired
	private ConvertToEquipmentDto toEquipmentDto;
	
	@Override
	public RealEstateDto convert(Property property) {
		RealEstateDto dto = null;
		if (property instanceof House) {
			dto = new HouseDto();
			dto = (HouseDto) setCommonFields(property, dto);
			((HouseDto) dto).setFloorsNumber(((House) property).getFloorsNumber());
		} else if (property instanceof Apartment) {
			dto = new ApartmentDto();
			dto = (ApartmentDto) setCommonFields(property, dto);
			((ApartmentDto) dto).setFloor(((Apartment) property).getFloor());
		}
		return dto;
	}

	private RealEstateDto setCommonFields(Property property, RealEstateDto dto) {
		dto.setArea(property.getArea());
		dto.setBooked(property.isBooked());
		dto.setEquipment(toEquipmentDto.convert(property.getEquipment()));
		dto.setHeatType(property.getHeatType());
		dto.setRoomsNumber(property.getRoomsNumber());
		dto.setId(property.getId());
		dto.setLocation(property.getLocation());
		return dto;
	}
	
}
