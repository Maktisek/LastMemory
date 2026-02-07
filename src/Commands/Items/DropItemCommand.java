package Commands.Items;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;

/**
 * Represents a command, which drops an item.
 *  <p>
 * {@link #name} stands for the name of the item to be dropped.
 * If the item is not found in player’s inventory, then no action is performed. Only the information about
 * the process is returned.
 * Otherwise, the item is dropped.
 * @author Matěj Pospíšil
 */
public class DropItemCommand implements Command {

    private final Player player;
    private final String name;

    public DropItemCommand(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    @Override
    public String execute() {
        if (player.getInventory().getWeight() == 0){
            Important.playSound("wrong sound");
            return Important.changeText("red", "Momentálně u sebe nemáš ani jeden předmět");
        }
        if (!player.getCurrentLocation().addItem(player.getInventory().dropItem(name))){
            Important.playSound("wrong sound");
            return Important.changeText("red", "Momentálně u sebe nemáš " + name);
        }
        Important.playSound("dropping sound");
        return "Položil si " + name + " do " + player.getCurrentLocation().getName();
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
