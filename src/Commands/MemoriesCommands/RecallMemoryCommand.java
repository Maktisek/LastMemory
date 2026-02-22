package Commands.MemoriesCommands;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;

/**
 * Represents a command, which displays a desired memory.
 * <p>
 * {@link #name} represents the name of the memory to be shown.
 * <p>
 * If the memory is not found, then a message is returned indicating that the memory was not found.
 * Otherwise, the specified memory is displayed to the player
 * @author Matěj Pospíšil
 */
public class RecallMemoryCommand implements Command {

    private final Player player;
    private final String name;
    private boolean isWaitAble;

    public RecallMemoryCommand(Player player, String name) {
        this.player = player;
        this.name = name;
        this.isWaitAble = true;
    }

    @Override
    public String execute() {
        if(player.getCollectedMemories().isEmpty()){
            Important.playSound("wrong sound");
            this.isWaitAble = false;
            return Important.changeText("red", "Momentálně nemáš žádnou vzpomínku");
        }
        if (!player.hasCollectedMemory(name)){
            Important.playSound("wrong sound");
            this.isWaitAble = false;
            return Important.writeSpace(60)+Important.changeText("red", "Vzpomínka " + Important.changeText("underline", name) + Important.changeText("red", " neexistuje"));
        }
        player.getCurrentLocation().getSong().pause();
        Important.playMusic("memory song");
        return player.writeMemory(name);
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean isWaitAble() {
        return isWaitAble;
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
        Important.stopMusic("memory song");
    }
}
