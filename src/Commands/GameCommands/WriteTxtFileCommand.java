package Commands.GameCommands;

import Commands.Command;
import Game.Important;

/**
 * Represents a command, which writes a chosen txt file. <p>
 * {@link #filePath} stands for the file path where the text file is located.
 * @author Matěj Pospíšil
 */
public class WriteTxtFileCommand implements Command {


    private final String filePath;

    public WriteTxtFileCommand(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String execute() {
        return Important.writeSpace(60)+Important.readTxtFiles(filePath, 0);
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
