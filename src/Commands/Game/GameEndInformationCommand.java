package Commands.Game;

import Commands.Command;
import Game.Important;

public class GameEndInformationCommand implements Command {
    @Override
    public String execute() {
        return Important.readTxtFiles("res\\TextFiles\\aboutGame.txt", 0);
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean isWaitAble() {
        return true;
    }

    @Override
    public boolean isTimeWaitAble() {
        return false;
    }

    @Override
    public boolean isContinuing() {
        return false;
    }

    @Override
    public void endAudio() {

    }
}
