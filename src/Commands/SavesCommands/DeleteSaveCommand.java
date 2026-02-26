package Commands.SavesCommands;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteSaveCommand implements Command {


    private final String save;
    private final Player player;

    public DeleteSaveCommand(String save, Player player) {
        this.save = save;
        this.player = player;
    }

    @Override
    public String execute() {
        if(save == null){
            Important.playSound("wrong sound");
            return Important.writeSpace(60)+Important.changeText("red", "Nyní není k dispozici žádný save");
        }

        Path path = Paths.get(System.getProperty("user.home"), "LastMemorySaves", save+".dat");
        if(!Files.exists(path)){
            Important.playSound("wrong sound");
            return Important.writeSpace(60)+Important.changeText("red", "Save s názvem " + save + " neexistuje");
        }
        try {
            Files.delete(path);
        } catch (IOException e) {
            Important.playSound("wrong sound");
            return Important.writeSpace(60)+Important.changeText("red", "Save " + save + " se nepodařilo vymazat");
        }
        Important.playSound("system");
        if(save.equalsIgnoreCase(player.getSave())){
            player.setSave(null);
        }
        return Important.writeSpace(60)+Important.changeText("green", "Save " + save + " se podařilo vymazat");
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
