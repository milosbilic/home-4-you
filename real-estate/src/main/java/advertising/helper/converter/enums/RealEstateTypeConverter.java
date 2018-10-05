package advertising.helper.converter.enums;

import java.beans.PropertyEditorSupport;

import advertising.enums.AdType;
import advertising.enums.RealEstateType;

public class RealEstateTypeConverter extends PropertyEditorSupport {
	public void setAsText(final String text) throws IllegalArgumentException {
		setValue(RealEstateType.fromValue(text));
	}

}
