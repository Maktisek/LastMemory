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
        if (player.getCurrentLocation().getItems().isEmpty()){
            return "Lokace " + player.getCurrentLocation().getName() + " neobsahuje žádné itemy";
        }
        Item temp = player.getCurrentLocation().hasItem(name);
        if(temp == null){
            return name + " se v lokaci nenachází";
        }
        if (!player.getInventory().checkAddCapacity(temp)){
            return "V inventáři není dostatek místa. Volné místo: " + player.getInventory().leftSpace() + ", Váha " + name + ": " + temp.getWeight();
        }
        if (player.getInventory().addItem(temp)) {
            player.getCurrentLocation().removeItem(temp);
        }
        return "Sebral si " + name;
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
}
