package home.four.you.exception;

import lombok.Getter;

import static home.four.you.exception.ErrorCode.BAD_REQUEST;

/**
 * Custom exception for various REST API errors.
 */
@Getter
public class Home4YouException extends RuntimeException {
    private static final long serialVersionUID = -6761797132862666016L;

    private final ErrorCode code;

    public Home4YouException() {
        this.code = BAD_REQUEST;
    }

    public Home4YouException(ErrorCode code) {
        this.code = code;
    }

    public Home4YouException(String message) {
        super(message);
        this.code = BAD_REQUEST;
    }

    public Home4YouException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public Home4YouException(ErrorCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public Home4YouException(ErrorCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
