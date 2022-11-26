package home.four.you.helper.converter.enums;

import java.beans.PropertyEditorSupport;

import home.four.you.enums.AdType;

public class AdTypeConverter extends PropertyEditorSupport {
	public void setAsText(final String text) throws IllegalArgumentException {
		setValue(AdType.fromValue(text));
	}

}
