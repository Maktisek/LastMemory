package Modes;

import AroundPlayer.Player;
import Game.Important;

/**
 * Mode designed to operate in the area of inventory
 */
public class BackpackMode implements Mode{

   private String forToString(Player player){
       return "Kapacita: " + player.getInventory().getWeight() + "/" + player.getInventory().getCapacity() + "\n" +
               "Sesbírané předměty: " + player.getInventory().writeItems() + "\n" +
               "Sesbírané vzpomínky: " + player.writeMemories() + "\n" +
               "Hotové úkoly: " + player.writeDoneTasks();
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
