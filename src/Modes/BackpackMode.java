package Modes;

import AroundPlayer.Player;
import Game.Important;

/**
 * A mode focused on inventory management and player possessions.
 * @author Matěj Pospíšil
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
        return Important.readTxtFiles("/TextFiles/backpackHelp.txt", 0);
    }

    @Override
    public ModeType getInfo() {
        return ModeType.backpack;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }
}
