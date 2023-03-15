package home.four.you.exception;

import static home.four.you.exception.ErrorCode.BAD_REQUEST;

/**
 * Exception class thrown when a bad request occurs.
 */
public class BadRequestException extends Home4YouException {


    public BadRequestException() {
        super(BAD_REQUEST);
    }

    public BadRequestException(String message) {
        super(BAD_REQUEST, message);
    }
}
