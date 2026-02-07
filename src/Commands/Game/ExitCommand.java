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
    public boolean isWaitAble() {
        return false;
    }

    @Override
    public boolean IsTimeWaitAble() {
        return false;
    }

    @Override
    public boolean isContinuing() {
        return true;
    }

    @Override
    public void endAudio() {

    }
}
