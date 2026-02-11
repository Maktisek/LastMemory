package Commands.TasksCommands;

import AroundPlayer.Memory;
import AroundPlayer.Player;
import Commands.Command;
import Game.Important;

/**
 * Represents a command, which evaluates a task.
 * <p>
 * This command always runs after {@link HandInCommand} is successfully done.
 * </p>
 * If the task is not done, then a message is returned indicating which items remain.
 * <p>
 * Otherwise, the memory associated with the player’s current task is given to the player
 * and a message is returned indicating that the evaluation was successful.
 * </p>
 *
 * @author Matěj Pospíšil
 */
public class EvaluateTaskCommand implements Command {

    private final Player player;

    public EvaluateTaskCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        if (!player.getCurrentTask().isDone()) {
            return Important.changeText("bold", "Další předměty k odevzdání: ") + player.getCurrentLocation().getFriendlyNPC().getTask().writeAllNeededItems();
        }
        Memory memory = player.getCurrentTask().giveMemory();
        player.addMemory(memory);
        player.deleteTask();
        player.getCurrentLocation().getFriendlyNPC().setTask(null);
        player.getCurrentLocation().getFriendlyNPC().switchSpeeches();
        player.getCurrentLocation().pauseMusic();
        Important.playSound("new memory");
        return Important.writeSpace(40) + "Ou, myslím, že si na něco vzpomínám...";
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
        Important.stopSound("new memory");
    }
}
