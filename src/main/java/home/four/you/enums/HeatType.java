package home.four.you.enums;

import java.util.Arrays;

import org.springframework.util.StringUtils;

public enum HeatType {

	CENTRAL ("central"), GAS ("gas"), WOOD ("wood");
	
	private String value;
	
	private HeatType(String value) {
		this.value = value;
	}
	
	public static HeatType fromValue(String value) {
		for (HeatType type : values()) {
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
