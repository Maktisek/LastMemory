package Items;

import AroundPlayer.Memory;
import AroundPlayer.Player;
import Game.Important;

import java.util.ArrayList;

/**
 * Represents a task in the game.
 * <p>
 * Each task has items that the player must hand over.
 * Items are stored not as {@link Item} instances, but as names and codes
 * in {@link #namesOfNeededObjects} and {@link #codesOfNeededObjects}.
 * </p>
 * <p>
 * Each task also has a reward, represented by a {@link Memory}.
 * </p>
 *
 * @author Matěj Pospíšil
 */
public class Task {

    private String name;
    private String description;
    private ArrayList<String> namesOfNeededObjects;
    private ArrayList<String> codesOfNeededObjects;
    private Memory memoryPrice;


    public Task() {
    }

    /**
     * Checks if the player has any of the items required to solve the task.
     *
     * @param player the player whose inventory will be checked
     * @return true if the player has at least one required item, false otherwise
     */
    public boolean canSolve(Player player) {
        for (String key : player.getInventory().getItems().keySet()) {
            if (codesOfNeededObjects.contains(key)) {
                return true;
            }

        }
        return false;
    }

    /**
     * Processes a task by handing over items from the player's inventory.
     * <p>
     * Iterates through the player's inventory keys and checks for matches with
     * {@link #codesOfNeededObjects}. If a match is found, the corresponding item’s name
     * and its code are removed from  {@link #namesOfNeededObjects} and {@link #codesOfNeededObjects}
     * </p>
     * <p>
     * All removed items are stored in a temporary {@code deletedItems} list,
     * which is then removed from the player's inventory.
     * </p>
     *
     * @param player the player whose inventory will be scanned for required items
     * @return a formatted String listing the items that were handed over,
     *         or an empty string if no items were handed over
     */
    public String scanAndSolveTask(Player player) {
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<Item> deletedItems = new ArrayList<>();
        for (String key : player.getInventory().getItems().keySet()) {
            if (codesOfNeededObjects.contains(key)) {
                Item solvedItem = player.getInventory().getItems().get(key).get(0);
                if (solvedItem != null) {
                    deletedItems.add(solvedItem);
                    temp.add(solvedItem.getName());
                    namesOfNeededObjects.remove(codesOfNeededObjects.indexOf(key));
                    codesOfNeededObjects.remove(key);
                }
            }
        }
        if (!temp.isEmpty()) {
            player.getInventory().removeMore(deletedItems);
            return Important.changeText("bold", "Odevzdané předměty: ") + Important.writeStringArrays(temp);
        }
        return "";
    }

    public boolean isDone() {
        return codesOfNeededObjects.isEmpty();
    }

    public Memory giveMemory() {
        Memory temp = memoryPrice;
        this.memoryPrice = null;
        return temp;
    }

    public String writeAllNeededItems() {
        return Important.writeStringArrays(namesOfNeededObjects);
    }

    /**
     * Prepares the task to be written through {@link Important#dashToString(String, String)}
     * @return the toString to be written through {@link Important#dashToString(String, String)}
     */
    public String forToString() {
        return this.description + "\n" +
                Important.changeText("bold", "Zbývající potřebné předměty k odevzdání: ") + writeAllNeededItems();
    }

    @Override
    public String toString() {
        return Important.dashToString(forToString(), "Úkol: " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getCodesOfNeededObjects() {
        return codesOfNeededObjects;
    }

    public void setCodesOfNeededObjects(ArrayList<String> codesOfNeededObjects) {
        this.codesOfNeededObjects = codesOfNeededObjects;
    }

    public Memory getMemoryPrice() {
        return memoryPrice;
    }

    public void setMemoryPrice(Memory memoryPrice) {
        this.memoryPrice = memoryPrice;
    }

    public ArrayList<String> getNamesOfNeededObjects() {
        return namesOfNeededObjects;
    }

    public void setNamesOfNeededObjects(ArrayList<String> namesOfNeededObjects) {
        this.namesOfNeededObjects = namesOfNeededObjects;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
