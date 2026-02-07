package Commands.Items;

import AroundPlayer.Player;
import Commands.Command;
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
            Important.playSound("wrong sound");
            return Important.changeText("red", "Momentálně u sebe nemáš " + name);
        }else {
            Important.playSound("wrong sound");
            return Important.changeText("red", "Momentálně u sebe nemáš ani jeden předmět");
        }
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
        return true;
    }

    @Override
    public void endAudio() {

    }
}
