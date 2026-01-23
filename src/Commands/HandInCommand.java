package Commands;

import AroundPlayer.Player;

public class HandInCommand implements Command{

    private Player player;

    public HandInCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        if(player.getCurrentLocation().getFriendlyNPC() != null && player.getCurrentLocation().getFriendlyNPC().getTask() != null) {
            String result = player.getCurrentTask().scanAndSolveTask(player);
            player.getCurrentLocation().getFriendlyNPC().setTask(player.getCurrentTask());
            return result;
        }
        return "Nelze nyní odevzdávat předměty do úkolu";
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
