package home.four.you.exception;

/**
 * Exception class for email existence error.
 */
public class EmailExistsException extends Exception {

    public EmailExistsException(String msg) {
        System.out.println(msg);
    }

}
