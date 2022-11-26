package home.four.you.converter.enums;

import java.beans.PropertyEditorSupport;

import home.four.you.model.HeatType;

public class HeatTypeConverter extends PropertyEditorSupport {
	public void setAsText(final String text) throws IllegalArgumentException {
		setValue(HeatType.fromValue(text));
	}
}
