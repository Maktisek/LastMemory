package Commands;

import AroundPlayer.Player;
import Game.Important;
import Items.Task;
import NPCS.FriendlyNPC;

/**
 * Command designed to accept task from current location friendlyNPC
 */
public class AcceptTaskCommand implements Command{

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
            return Important.changeText("green", "Nový úkol " + friendlyNPC.getTask().getName() + " byl přijat");
        }
        Important.playSound("wrong sound");
        return "Nyní nemůžeš přijmat žádné úkoly";
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean waitAble() {
        return true;
    }

    @Override
    public boolean timeWaitAble() {
        return false;
    }

    @Override
    public boolean continuing() {
        return true;
    }


    @Override
    public void endAudio() {

    }
}
