package Commands;

import AroundPlayer.Player;
import Game.Important;

public class RecallMemoryCommand implements Command{

    private final Player player;
    private final String name;

    public RecallMemoryCommand(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    @Override
    public String execute() {
        if (!player.hasCollectedMemory(name)){
            return "Vzpomínka " + name + " neexistuje";
        }
        return player.writeMemory(name);
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
        Important.stopMusic(name);
    }
}
