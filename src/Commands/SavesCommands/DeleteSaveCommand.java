package Commands.SavesCommands;

import Commands.Command;
import Game.Important;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteSaveCommand implements Command {


    private final String save;

    public DeleteSaveCommand(String save) {
        this.save = save;
    }

    @Override
    public String execute() {
        Path path = Paths.get(System.getProperty("user.home"), "LastMemorySaves", save+".dat");
        if(!Files.exists(path)){
            return Important.writeSpace(60)+Important.changeText("red", "Save s názvem " + save + " neexistuje");
        }
        try {
            Files.delete(path);
        } catch (IOException e) {
            return Important.writeSpace(60)+Important.changeText("red", "Save " + save + " se nepodařilo vymazat");
        }
        return Important.writeSpace(60)+Important.changeText("green", "Save " + save + " se podařilo vymazat");
    }

    @Override
    public boolean exit() {
        return false;
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
        return false;
    }

    @Override
    public void endAudio() {

    }
}
