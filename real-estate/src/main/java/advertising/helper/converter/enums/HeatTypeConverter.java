package advertising.helper.converter.enums;

import java.beans.PropertyEditorSupport;

import advertising.enums.HeatType;

public class HeatTypeConverter extends PropertyEditorSupport {
	public void setAsText(final String text) throws IllegalArgumentException {
		setValue(HeatType.fromValue(text));
	}
}
