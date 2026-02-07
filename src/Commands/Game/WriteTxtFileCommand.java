package Commands.Game;

import Commands.Command;
import Game.Important;

public class WriteTxtFileCommand implements Command {


    private final String filePath;

    public WriteTxtFileCommand(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String execute() {
        return Important.readTxtFiles(filePath, 0);
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
