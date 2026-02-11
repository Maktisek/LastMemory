package Commands.TasksCommands;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;

/**
 * Represents a command, which displays information about ongoing player’s task.
 * <p>
 *     If the player has no currently ongoing task, then no action is performed and a message is returned
 *     indicating that there is no accepted task at the moment.
 * </p>
 * Otherwise, the current task is displayed to the player.
 * @author Matěj Pospíšil
 */
public class ShowCurrentTaskCommand implements Command {

    private final Player player;

    public ShowCurrentTaskCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        if(player.getCurrentTask() != null){
            return player.getCurrentTask().toString();
        }
        Important.playSound("wrong sound");
        return Important.changeText("red", "Momentálně nemáš přijmutý žádný úkol");
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
