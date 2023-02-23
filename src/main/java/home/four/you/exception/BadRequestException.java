package home.four.you.exception;

/**
 * Exception class thrown when a bad request occurs.
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
