package  com.daon.dev.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class HttpClientErrorException extends RuntimeException {
	
	private static final long serialVersionUID = -2288703006613941931L;

	public HttpClientErrorException(String message) {
        super(message);
    }

    public HttpClientErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
