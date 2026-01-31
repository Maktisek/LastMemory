package Commands;

import AroundPlayer.Player;
import Game.Important;

public class HandInCommand implements Command{

    private final Player player;
    private boolean continues;

    public HandInCommand(Player player) {
        this.player = player;
        this.continues = true;
    }

    @Override
    public String execute() {
        if(player.getCurrentLocation().getFriendlyNPC() != null && player.getCurrentLocation().getFriendlyNPC().getTask() != null && player.getCurrentTask() != null && player.getCurrentTask().getName().equalsIgnoreCase(player.getCurrentLocation().getFriendlyNPC().getTask().getName())) {
            String result = player.getCurrentTask().scanAndSolveTask(player);
            player.getCurrentLocation().getFriendlyNPC().setTask(player.getCurrentTask());
            return result;
        }
        continues = false;
        Important.playSound("wrong sound");
        return "Nelze nyní odevzdávat předměty do úkolu";
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean waitAble() {
        return false;
    }

    @Override
    public boolean timeWaitAble() {
        return false;
    }

    @Override
    public boolean continuing() {
        return continues;
    }

    @Override
    public void endAudio() {

    }
}
