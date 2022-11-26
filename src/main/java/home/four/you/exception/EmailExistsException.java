package home.four.you.exception;

public class EmailExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmailExistsException(String msg) {
		System.out.println(msg);
	}

}
