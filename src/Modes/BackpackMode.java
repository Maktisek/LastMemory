package Modes;

import AroundPlayer.Player;
import Game.Important;

/**
 * Mode designed to operate in the area of inventory
 */
public class BackpackMode implements Mode {

    private String forToString(Player player) {
        return Important.changeText("bold", "Kapacita: ") + player.getInventory().writeWeight() + "/" + player.getInventory().getCapacity() + "\n" +
                Important.changeText("bold", "Sesbírané předměty: ") + player.getInventory().writeItems() + "\n" +
                Important.changeText("bold", "Sesbírané vzpomínky: ") + player.writeMemories() + "\n" +
                Important.changeText("bold", "Hotové úkoly: ") + player.writeDoneTasks();
    }

    @Override
    public String executeInfo(Player player) {
        return Important.dashToString(forToString(player), "Batoh");
    }

    @Override
    public String executeHelp() {
        return Important.readTxtFiles("res\\backpackHelp.txt", 0);
    }

    @Override
    public String getInfo() {
        return "backpack";
    }

    @Override
    public boolean special() {
        return false;
    }


}
