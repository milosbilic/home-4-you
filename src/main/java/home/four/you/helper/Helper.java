package home.four.you.helper;

import home.four.you.model.entity.Apartment;
import org.springframework.stereotype.Component;

import home.four.you.model.entity.House;

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
