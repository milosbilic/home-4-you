package home.four.you.factory;

import home.four.you.model.dto.AdDto;
import home.four.you.model.dto.ApartmentAdDto;
import home.four.you.model.dto.HouseAdDto;
import home.four.you.model.PropertyType;
import home.four.you.exception.UnsupportedTypeException;

public class AdDtoFactory {

	public static AdDto getInstance(PropertyType propertyType) {
		AdDto adDto = null;
		switch (propertyType) {
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
