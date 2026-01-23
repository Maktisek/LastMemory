package Commands;

import AroundPlayer.Player;

public class RecallMemoryCommand implements Command{

    private Player player;
    private String name;

    public RecallMemoryCommand(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    @Override
    public String execute() {
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
}
