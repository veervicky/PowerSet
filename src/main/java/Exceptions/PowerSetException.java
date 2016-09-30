package Exceptions;

/**
 * Created by Vicky on 28/09/16.
 */
public class PowerSetException extends RuntimeException {

    public PowerSetException() {
    }

    public PowerSetException(String message) {
        super(message);
    }

    public PowerSetException(String message, Throwable cause) {
        super(message, cause);
    }
}
