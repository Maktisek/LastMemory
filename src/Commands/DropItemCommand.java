package Commands;

import AroundPlayer.Player;
import Game.Important;

public class DropItemCommand implements Command {

    private final Player player;
    private final String name;

    public DropItemCommand(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    @Override
    public String execute() {
        if (player.getInventory().getWeight() != 0) {
            if (player.getCurrentLocation().addItem(player.getInventory().dropItem(name))) {
                Important.playSound("dropping sound");
                return "Položil si " + name + " do " + player.getCurrentLocation().getName();
            }
            return Important.changeText("red", "Momentálně u sebe nemáš " + name);
        }else {
            return Important.changeText("red", "Momentálně u sebe nemáš ani jeden předmět");
        }
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean waitAble() {
        return false;
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

    }
}
