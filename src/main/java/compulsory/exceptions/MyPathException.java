package compulsory.exceptions;

/**
 * a custom exception for invalid path
 */
public class MyPathException extends Exception {

    public MyPathException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyPathException(String message) {
        super(message);
    }
}
