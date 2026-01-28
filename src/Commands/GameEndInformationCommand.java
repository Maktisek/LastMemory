package Commands;

import Game.Important;

public class GameEndInformationCommand implements Command{
    @Override
    public String execute() {
        return Important.readTxtFiles("res\\aboutGame.txt");
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
        return false;
    }

    @Override
    public void endAudio() {

    }
}
