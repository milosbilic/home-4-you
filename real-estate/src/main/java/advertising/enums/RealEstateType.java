package advertising.enums;

import java.util.Arrays;

public enum RealEstateType {

	HOUSE("house"), APARTMENT("apartment");
	
	private String value;
	
	RealEstateType(String value) {
		this.value = value;
	}

	public static Object fromValue(String value) {
		for (RealEstateType type : values()) {
			if (type.value.equalsIgnoreCase(value)) {
				return type;
			}
			throw new IllegalArgumentException(
					"Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
		}
		return null;
	}
	
}
