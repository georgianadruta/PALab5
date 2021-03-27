package compulsory.exceptions;

/**
 * a custom exception for invalid data
 */
public class MyInvalidDataException extends Exception {

    public MyInvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyInvalidDataException(String message) {
        super(message);
    }
}
