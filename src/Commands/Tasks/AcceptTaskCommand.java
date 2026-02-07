package Commands.Tasks;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;
import NPCS.FriendlyNPC;

/**
 * Command designed to accept task from current location friendlyNPC
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
            return Important.changeText("red", "V lokaci se nyní nikdo nenachází");
        }
        if(friendlyNPC.getTask() == null){
            Important.playSound("wrong sound");
            return Important.changeText("red", friendlyNPC.getName() + " žádný úkol nenabízí");
        }
        if (player.addCurrentTask(friendlyNPC.getTask())){
            Important.playSound("new task");
            return Important.changeText("green", "Nový úkol " + friendlyNPC.getTask().getName() + " byl přijat");
        }
        Important.playSound("wrong sound");
        return Important.changeText("red", "Nyní nemůžeš přijmat žádné úkoly");
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
    public boolean IsTimeWaitAble() {
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
