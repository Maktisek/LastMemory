package Commands.TasksCommands;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;
import NPCS.FriendlyNPC;

/**
 * Represents a command, which accepts a task from {@link FriendlyNPC}.
 * <p>
 *     If there is no friendly NPC in player’s current location or the friendly NPC has no available task,
 *     then no action is performed, and a message is returned indicating that no task can be accepted at the moment.
 * </p>
 * If the player currently has an ongoing task, then no action is performed and a message is returned
 * indicating that there is currently an ongoing task.
 * <p>
 *     Otherwise, the task is accepted and a message is returned indicating that the task was successfully accepted.
 * </p>
 * @author Matěj Pospíšil
 */
public class AcceptTaskCommand implements Command {

    private final Player player;

    public AcceptTaskCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        FriendlyNPC friendlyNPC = player.getCurrentLocation().getFriendlyNPC();
        if(friendlyNPC == null){
            Important.playSound("wrong sound");
            return Important.writeSpace(40)+Important.changeText("red", "V lokaci se nyní nikdo nenachází");
        }
        if(friendlyNPC.getTask() == null){
            Important.playSound("wrong sound");
            return Important.writeSpace(40)+Important.changeText("red", friendlyNPC.getName() + " žádný úkol nenabízí");
        }
        if (player.addCurrentTask(friendlyNPC.getTask())){
            Important.playSound("new task");
            return Important.writeSpace(40)+Important.changeText("green", "Nový úkol " + friendlyNPC.getTask().getName() + " byl přijat");
        }
        Important.playSound("wrong sound");
        return Important.writeSpace(40)+Important.changeText("red", "Nyní nemůžeš přijmat žádné úkoly");
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
