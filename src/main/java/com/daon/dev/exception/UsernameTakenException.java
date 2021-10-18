package  com.daon.dev.exception;

public class UsernameTakenException extends Exception {
    
	private static final long serialVersionUID = -2400288962410190997L;

	public UsernameTakenException(String message) {
        super(message);
    }

    public UsernameTakenException(String message, Throwable cause) {
        super(message, cause);
    }
}
