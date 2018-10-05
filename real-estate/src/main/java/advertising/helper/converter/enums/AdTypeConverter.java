package advertising.helper.converter.enums;

import java.beans.PropertyEditorSupport;

import advertising.enums.AdType;

public class AdTypeConverter extends PropertyEditorSupport {
	public void setAsText(final String text) throws IllegalArgumentException {
		setValue(AdType.fromValue(text));
	}

}
