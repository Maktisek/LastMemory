package Commands;

import AroundPlayer.Player;
import Items.Task;

/**
 * Command designed to accept task from current location friendlyNPC
 */
public class AcceptTaskCommand implements Command{

    private Player player;

    public AcceptTaskCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        if(player.getCurrentLocation().getFriendlyNPC() != null){
            Task temp = player.getCurrentLocation().getFriendlyNPC().getTask();
            if (temp != null){
                if(player.addCurrentTask(temp)){
                    return "Nový úkol " + temp.getName() + " byl přijat";
                }else {
                    return "Nyní nemůžeš přijmat žádné úkoly";
                }
            }else {
                return player.getCurrentLocation().getFriendlyNPC().getName() + " již žádný úkol nenabízí";
            }
        }else {
            return "V lokaci se nyní nikdo nenachází ";
        }

    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean waitAble() {
        return true;
    }
}
