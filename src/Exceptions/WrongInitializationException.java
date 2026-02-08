package Exceptions;
/**
 * Thrown when the game or one of its components is initialized incorrectly.
 * @author Matěj Pospíšil
 */
public class WrongInitializationException extends Exception {
    public WrongInitializationException(String message) {
        super(message);
    }
}
