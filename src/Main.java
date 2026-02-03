import Exceptions.WrongInitializationException;
import Game.Console;

public class Main {
    public static void main(String[] args) {

        try {
            Console console = new Console();
            console.start();
        } catch (WrongInitializationException e) {
            System.out.println(e.getMessage());
        }
    }
}