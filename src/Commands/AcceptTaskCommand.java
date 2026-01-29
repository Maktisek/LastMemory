package Commands;

import AroundPlayer.Player;
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
            return "V lokaci se nyní nikdo nenachází";
        }
        if(friendlyNPC.getTask() == null){
            return friendlyNPC.getName() + " žádný úkol nenabízí";
        }
        if (player.addCurrentTask(friendlyNPC.getTask())){
            return "Nový úkol " + friendlyNPC.getTask().getName() + " byl přijat";
        }
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
