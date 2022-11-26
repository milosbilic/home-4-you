package home.four.you.helper.converter.enums;

import java.beans.PropertyEditorSupport;

import home.four.you.enums.RealEstateType;

public class RealEstateTypeConverter extends PropertyEditorSupport {
	public void setAsText(final String text) throws IllegalArgumentException {
		setValue(RealEstateType.fromValue(text));
	}

}
