package advertising.enums;

import java.util.Arrays;

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
			throw new IllegalArgumentException(
					"Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
		}
		return null;
	}

}
