package Modes;

import AroundPlayer.Player;

public class BackpackMode implements Mode{
    @Override
    public String executeInfo(Player player) {
        return "------------------Batoh------------------" + "\n" +
                "Kapacita: " + player.getInventory().getWeight() + "/" + player.getInventory().getCapacity() + "\n" +
                "Sesbírané předměty: " + player.getInventory().writeItems() + "\n" +
                "Sesbírané vzpomínky: " + player.writeMemories() + "\n" +
                "Hotové úkoly: " + player.writeDoneTasks();
    }

    @Override
    public String executeHelp(Player player) {
        return null;
    }
}
