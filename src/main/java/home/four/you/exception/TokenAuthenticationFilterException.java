package home.four.you.exception;

/**
 * Exception class used for exceptions in token authentication filter.
 */
public class TokenAuthenticationFilterException extends Exception {

  private static final long serialVersionUID = 4828785204287154283L;

  public TokenAuthenticationFilterException() {
    super("Token authentication exception");
  }
}
