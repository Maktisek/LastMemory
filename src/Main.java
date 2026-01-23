import Game.Console;

public class Main {
    public static void main(String[] args) {

        try {
            Console console = new Console();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}