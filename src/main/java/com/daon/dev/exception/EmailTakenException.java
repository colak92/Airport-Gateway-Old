package  com.daon.dev.exception;

public class EmailTakenException extends Exception {
	
	private static final long serialVersionUID = -8415703076175769604L;

	public EmailTakenException(String message) {
        super(message);
    }

    public EmailTakenException(String message, Throwable cause) {
        super(message, cause);
    }
}
