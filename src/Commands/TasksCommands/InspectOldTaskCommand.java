package Commands.TasksCommands;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;

/**
 * Represents a command, which displays information about desired done task.
 * <p>
 *     {@link #name} represents the name of the desired completed task.
 * </p>
 * If no task with the same name as {@link #name} was found, then a message is returned
 * indicating that no task named {@link #name} exists.
 * <p>
 *     Otherwise, the desired task is shown to the player.
 * </p>
 * @author Matěj Pospíšil
 */
public class InspectOldTaskCommand implements Command {

    private final Player player;
    private final String name;

    public InspectOldTaskCommand(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    @Override
    public String execute() {
        if(player.hasOldTask(name)){
            return Important.writeSpace(60)+player.writeOldTask(name);
        }
        Important.playSound("wrong sound");
        return Important.writeSpace(60)+Important.changeText("red", "Úkol " + name + " neexistuje");
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
