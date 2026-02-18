package Commands.TasksCommands;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;

/**
 * Represents a command, which hands in all possible items to {@link NPCS.FriendlyNPC}.
 * <p>
 *     If the player has no task accepted, no friendly NPC is currently in player’s current location,
 *     the friendly NPC has no task or the player’s task does not match with friendly NPC’s task,
 *     then no action is performed, {@link #continues} is set to false
 *     and a message is returned indicating that there is no one to hand items to.
 * </p>
 * If player has no items, which could be handed in, then no action is performed, {@link #continues} is set to false
 * and a message is returned indicating that there is nothing to be handed in.
 * <p>
 *     Otherwise, all items that can be handed in are handed over and a message is returned
 *     indicating which items have been handed in.
 * </p>
 * @author Matěj Pospíšil
 */
public class HandInCommand implements Command {

    private final Player player;
    private boolean continues;

    public HandInCommand(Player player) {
        this.player = player;
        this.continues = true;
    }

    @Override
    public String execute() {
        if(player.getCurrentTask() == null){
            Important.playSound("wrong sound");
            continues = false;
            return Important.writeSpace(60)+Important.changeText("red", "Momentálně nemáš žádný aktivní úkol");
        }
        if(player.getCurrentLocation().getFriendlyNPC() == null){
            Important.playSound("wrong sound");
            continues = false;
            return Important.writeSpace(60)+Important.changeText("red", "Tady není komu odevzdávat předměty");
        }
        if(player.getCurrentLocation().getFriendlyNPC().getTask() == null){
            Important.playSound("wrong sound");
            continues = false;
            return Important.writeSpace(60)+Important.changeText("red", "U "+player.getCurrentLocation().getFriendlyNPC().getName() + " nemáš žádný aktivní úkol.");
        }
        if(!player.getCurrentTask().getName().equalsIgnoreCase(player.getCurrentLocation().getFriendlyNPC().getTask().getName())){
            Important.playSound("wrong sound");
            continues = false;
            return Important.writeSpace(60)+Important.changeText("red", player.getCurrentLocation().getFriendlyNPC().getName() + " ti nezadal " + player.getCurrentTask().getName());
        }
        if(!player.getCurrentTask().canSolve(player)){
            Important.playSound("wrong sound");
            continues = false;
            return Important.writeSpace(60)+Important.changeText("red", "Nelze odevzdat žádný předmět");
        }
        String result = player.getCurrentTask().scanAndSolveTask(player);
        player.getCurrentLocation().getFriendlyNPC().setTask(player.getCurrentTask());
        Important.playSound("hand in");
        return Important.writeSpace(60)+result;
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
        return true;
    }

    @Override
    public boolean isContinuing() {
        return continues;
    }

    @Override
    public void endAudio() {

    }
}
