package Commands;

import AroundPlayer.Player;
import Game.Important;
import Items.Item;

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
