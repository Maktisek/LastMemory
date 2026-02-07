package Commands.Game;

import Commands.Command;
import Game.Important;

public class GameInformationCommand implements Command {
    @Override
    public String execute() {
        return Important.readTxtFiles("res\\TextFiles\\howToPlay.txt", 0);
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
        return true;
    }

    @Override
    public void endAudio() {

    }
}
