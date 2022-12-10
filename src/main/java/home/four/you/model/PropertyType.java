package home.four.you.model;

import java.util.Arrays;

import org.springframework.util.StringUtils;

public enum PropertyType {

	HOUSE("house"), APARTMENT("apartment");
	
	private String value;
	
	PropertyType(String value) {
		this.value = value;
	}

	public static PropertyType fromValue(String value) {
		for (PropertyType type : values()) {
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
