package Commands.Game;

import Commands.Command;

/**
 * Represents a command, which exists the game.
 * @author Matěj Pospíšil
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
    public boolean isTimeWaitAble() {
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
