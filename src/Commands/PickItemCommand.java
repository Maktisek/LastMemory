package Commands;

import AroundPlayer.Player;

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
            if (player.getInventory().addItem(player.getCurrentLocation().findAndRemoveItem(name))) {
                return "Sebral si " + name;
            }
            return "Item " + name + " se v lokaci nenachází";
        }else {
            return "Lokace " + player.getCurrentLocation().getName() + " neobsahuje žádné itemy";
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
}
