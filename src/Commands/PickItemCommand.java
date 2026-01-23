package Commands;

import AroundPlayer.Player;
import Items.Item;

public class PickItemCommand implements Command {

    private Player player;
    private String name;

    public PickItemCommand(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    @Override
    public String execute() {
        if (!player.getCurrentLocation().getItems().isEmpty()) {
            Item temp = player.getCurrentLocation().findAndRemoveItem(name);
            if (temp != null) {
                if (player.getInventory().checkAddCapacity(temp)) {
                    if (player.getInventory().addItem(temp)) {
                        return "Sebral si " + name;
                    }
                } else {
                    return "V inventáři není dostatek místa. Volné místo: " + player.getInventory().leftSpace() + ", Váha " + name + ": " + temp.getWeight();
                }
            }
        } else {
            return "Lokace " + player.getCurrentLocation().getName() + " neobsahuje žádné itemy";
        }
        return name + " se v lokaci nenachází";
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean waitAble() {
        return false;
    }
}
