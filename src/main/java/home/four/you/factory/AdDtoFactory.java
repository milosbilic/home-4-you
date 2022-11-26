package home.four.you.factory;

import home.four.you.dto.AdDto;
import home.four.you.dto.ApartmentAdDto;
import home.four.you.dto.HouseAdDto;
import home.four.you.enums.RealEstateType;
import home.four.you.exception.UnsupportedTypeException;

public class AdDtoFactory {

	public static AdDto getInstance(RealEstateType realEstateType) {
		AdDto adDto = null;
		switch (realEstateType) {
		case HOUSE:
			adDto = new HouseAdDto();
			break;
		case APARTMENT:
			adDto = new ApartmentAdDto();
			break;
		default:
			throw new UnsupportedTypeException("The passed type doesn't exist!");
		}
		return adDto;
	}

}
