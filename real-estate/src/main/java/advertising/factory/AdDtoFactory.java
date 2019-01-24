package advertising.factory;

import advertising.dto.AdDto;
import advertising.dto.ApartmentAdDto;
import advertising.dto.HouseAdDto;
import advertising.enums.RealEstateType;
import advertising.exception.UnsupportedTypeException;

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
