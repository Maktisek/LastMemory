package Commands.Game;

import Commands.Command;

/**
 * Command designed to turn of the game
 */
public class ExitCommand implements Command {
    @Override
    public String execute() {
        return "Hra se vypíná";
    }

    @Override
    public boolean exit() {
        return true;
    }

    @Override
    public boolean waitAble() {
        return false;
    }

    @Override
    public boolean timeWaitAble() {
        return false;
    }

    @Override
    public boolean continuing() {
        return true;
    }

    @Override
    public void endAudio() {

    }
}
