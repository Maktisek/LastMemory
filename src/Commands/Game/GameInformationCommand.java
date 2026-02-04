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
    public boolean waitAble() {
        return true;
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
