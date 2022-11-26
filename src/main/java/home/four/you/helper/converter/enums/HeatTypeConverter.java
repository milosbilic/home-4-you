package home.four.you.helper.converter.enums;

import java.beans.PropertyEditorSupport;

import home.four.you.enums.HeatType;

public class HeatTypeConverter extends PropertyEditorSupport {
	public void setAsText(final String text) throws IllegalArgumentException {
		setValue(HeatType.fromValue(text));
	}
}
