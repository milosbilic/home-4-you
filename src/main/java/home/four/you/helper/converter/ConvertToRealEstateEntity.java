package home.four.you.helper.converter;

import home.four.you.dto.ApartmentDto;
import home.four.you.dto.HouseDto;
import home.four.you.model.entity.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import home.four.you.dto.RealEstateDto;
import home.four.you.model.entity.House;
import home.four.you.model.entity.RealEstate;

@Component
public class ConvertToRealEstateEntity implements Converter<RealEstateDto, RealEstate> {

	@Autowired
	private ConvertToEquipmentEntity toEquipment;
	
	@Override
	public RealEstate convert(RealEstateDto dto) {
		RealEstate re = null;

		if (dto instanceof HouseDto) {
			re = new House();
			re = (House) setCommonFields(dto, re);
			((House) re).setFloorsNumber(((HouseDto) dto).getFloorsNumber());
		} else if(dto instanceof ApartmentDto) {
			re = new Apartment();
			re = (Apartment) setCommonFields(dto, re);
			((Apartment) re).setFloor(((ApartmentDto) dto).getFloor());
		}
		return re;
	}
	
	private RealEstate setCommonFields(RealEstateDto dto, RealEstate re) {
		re.setArea(dto.getArea());
		re.setBooked(dto.isBooked());
		re.setHeatType(dto.getHeatType());
		re.setImage(dto.getImage());
		re.setLocation(dto.getLocation());
		re.setRoomsNumber(dto.getRoomsNumber());
		if (dto.getEquipment() != null) {
			re.setEquipment(toEquipment.convert(dto.getEquipment()));
		}
		return re;
	}

}
