import Game.Console;
import Game.Important;

public class Main {
    public static void main(String[] args) {

        Important.playMusic("třetí patro");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Important.pause("třetí patro");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Important.resume("třetí patro");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        try {
            Console console = new Console();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}