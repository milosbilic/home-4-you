package home.four.you.enums;

import java.util.Arrays;

import org.springframework.util.StringUtils;

public enum RealEstateType {

	HOUSE("house"), APARTMENT("apartment");
	
	private String value;
	
	RealEstateType(String value) {
		this.value = value;
	}

	public static RealEstateType fromValue(String value) {
		for (RealEstateType type : values()) {
			if (type.value.equalsIgnoreCase(value)) {
				return type;
			}
		}
		throw new IllegalArgumentException(
				"Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
	}
	
	public String toString() {
		return StringUtils.capitalize(value);
	}
	
}
