package Commands;

import AroundPlayer.Player;

public class ShowCurrentTaskCommand implements Command{

    private Player player;

    public ShowCurrentTaskCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        if(player.getCurrentTask() != null){
            return player.getCurrentTask().toString();
        }
        return "Momentálně nemáš přijmutý žádný úkol";
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
}
