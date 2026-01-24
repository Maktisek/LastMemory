package Commands;

import AroundPlayer.Player;
import Game.Important;

public class InspectItemCommand implements Command{

    private Player player;
    private String name;

    public InspectItemCommand(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    @Override
    public String execute() {
        return Important.writeLongTexts(player.getInventory().descriptionItem(name));
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
}
