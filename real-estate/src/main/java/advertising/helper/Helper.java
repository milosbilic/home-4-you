package advertising.helper;

import org.springframework.stereotype.Component;

import advertising.model.Apartment;
import advertising.model.House;

@Component
public class Helper {

	private static final String HOUSE = "House";
	
	public static Class<?> getRealEstateClass(String className) {
		if (className.equalsIgnoreCase(HOUSE)) {
			return House.class;
		}
		return Apartment.class;
	}
	
}
