package Commands.Items;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;
import Items.Item;

/**
 * Represents a command, which picks up a desired item from player’s current location.
 * <p>
 * {@link #name} represents the name of the item to be picked up.
 * <p>
 * If the location is empty, the item is not found or player’s inventory capacity is insufficient, then no action is performed.
 * Only the information about the process is returned.
 * Otherwise, the desired item is transferred to the player and removed from the current location
 * @author Matěj Pospíšil
 */
public class PickItemCommand implements Command {

    private final Player player;
    private final String name;

    public PickItemCommand(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    @Override
    public String execute() {
        if (player.getCurrentLocation().getItems().isEmpty()){
            Important.playSound("wrong sound");
            return Important.changeText("red", "Lokace " + player.getCurrentLocation().getName() + " neobsahuje žádné itemy");
        }
        Item temp = player.getCurrentLocation().hasItem(name);
        if(temp == null){
            Important.playSound("wrong sound");
            return Important.changeText("red", name + " se v lokaci nenachází");
        }
        if (!player.getInventory().checkAddCapacity(temp)){
            Important.playSound("wrong sound");
            return Important.changeText("red", "V inventáři není dostatek místa. Volné místo: " + player.getInventory().leftSpace() + ", Váha " + name + ": " + temp.getWeight());
        }
        if (player.getInventory().addItem(temp)) {
            player.getCurrentLocation().removeItem(temp);
        }
        Important.playSound("picking sound");
        return Important.changeText("green", "Sebral si " + name);
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
