package Commands.GameCommands;

import Commands.Command;
import Game.Important;

/**
 * Represents a command, which exists the game.
 * @author Matěj Pospíšil
 */
public class ExitCommand implements Command {

    @Override
    public String execute() {
        Important.playSound("opustit");
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
        return true;
    }

    @Override
    public boolean isContinuing() {
        return true;
    }

    @Override
    public void endAudio() {

    }
}
