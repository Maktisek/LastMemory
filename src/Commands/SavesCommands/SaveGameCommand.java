package Commands.SavesCommands;

import Commands.Command;
import Exceptions.WrongInitializationException;
import Game.Important;
import Game.Initialization;

public class SaveGameCommand implements Command {


    private Initialization init;
    private String save;

    public SaveGameCommand(Initialization init, String save) {
        this.init = init;
        this.save = save;
    }

    @Override
    public String execute() {
        try {
            init.writeToFile(save);
        } catch (WrongInitializationException e) {
            Important.playSound("wrong sound");
            return Important.writeSpace(60)+Important.changeText("red", "Save " + save + " neexistuje");
        }
        Important.playSound("system");
        return Important.writeSpace(60)+Important.changeText("green" ,"Hra byla úspěšně uložena do " + save);
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
