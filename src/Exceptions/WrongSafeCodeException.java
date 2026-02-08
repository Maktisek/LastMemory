package Exceptions;
/**
 * Thrown when the code of the safe is inserted incorrectly.
 * @author Matěj Pospíšil
 */
public class WrongSafeCodeException extends Exception {
    public WrongSafeCodeException(String message) {
        super(message);
    }
}
