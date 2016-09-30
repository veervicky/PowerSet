package Exceptions;

/**
 * Created by Vicky on 28/09/16.
 */

/**
 * A base wrapper Exception class to wrap all exceptions occuring in Power Set project.
 */
public class PowerSetException extends Exception {

    public PowerSetException() {
    }

    public PowerSetException(String message) {
        super(message);
    }

    public PowerSetException(String message, Throwable cause) {
        super(message, cause);
    }
}
