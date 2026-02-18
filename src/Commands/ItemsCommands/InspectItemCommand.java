package Commands.ItemsCommands;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;

/**
 * Represents a command, which writes the description of desired item.
 * <p>
 * {@link #name} represents the name of the desired item.
 * <p>
 * If the item is not found in player’s inventory or the inventory is empty, then no action is performed.
 * Only the information about the process is returned.
 * Otherwise, the description is shown.
 * @author Matěj Pospíšil
 */
public class InspectItemCommand implements Command {

    private final Player player;
    private final String name;

    public InspectItemCommand(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    @Override
    public String execute() {
        if(player.getInventory().isEmpty()){
            Important.playSound("wrong sound");
            return Important.writeSpace(60)+Important.changeText("red", "Inventář je prázdný, žádný předmět nelze prohlédnout");
        }

        String result = player.getInventory().descriptionItem(name);

        if(result == null){
            Important.playSound("wrong sound");
            return Important.writeSpace(60)+Important.changeText("red", "Item " + name + " se v inventáři nenachází.");
        }

        return result;
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean isWaitAble() {
        return true;
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
