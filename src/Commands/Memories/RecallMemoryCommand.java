package Commands.Memories;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;

public class RecallMemoryCommand implements Command {

    private final Player player;
    private final String name;

    public RecallMemoryCommand(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    @Override
    public String execute() {
        if (!player.hasCollectedMemory(name)){
            Important.playSound("wrong sound");
            return "Vzpomínka " + name + " neexistuje";
        }
        player.getCurrentLocation().getSong().pause();
        Important.playMusic("memory song");


        return player.writeMemory(name);
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
    public boolean IsTimeWaitAble() {
        return false;
    }

    @Override
    public boolean isContinuing() {
        return true;
    }

    @Override
    public void endAudio() {
        Important.stopMusic("memory song");
    }
}
