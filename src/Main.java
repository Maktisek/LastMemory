import Exceptions.WrongInitializationException;
import Game.Console;
import Game.Important;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        String letter = Important.loadText();
        char test = letter.charAt(0);
        System.out.println((int) test);

        try {
            Console console = new Console();
            console.start();
        } catch (WrongInitializationException e) {
            System.out.println(e.getMessage());
        }
    }
}