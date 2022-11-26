package home.four.you.converter.enums;

import java.beans.PropertyEditorSupport;

import home.four.you.model.RealEstateType;

public class RealEstateTypeConverter extends PropertyEditorSupport {
	public void setAsText(final String text) throws IllegalArgumentException {
		setValue(RealEstateType.fromValue(text));
	}

}
