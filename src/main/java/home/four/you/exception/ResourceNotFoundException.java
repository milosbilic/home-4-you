package home.four.you.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static home.four.you.exception.ErrorCode.NOT_FOUND;

/**
 * Exception class for resource not found error.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Home4YouException {

	private static final long serialVersionUID = 3346530278343930771L;

	public ResourceNotFoundException() {
		super(NOT_FOUND);
	}
}
