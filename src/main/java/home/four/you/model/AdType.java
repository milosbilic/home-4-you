package home.four.you.model;

import java.util.Arrays;

import org.springframework.util.StringUtils;

public enum AdType {

	SALE("sale"), PURCHASE("purchase"), RENT("rent"), LEASE("lease");

	private String value;

	AdType(String value) {
		this.value = value;
	}

	public static AdType fromValue(String value) {
		for (AdType type : values()) {
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
