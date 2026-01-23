package Commands;

import AroundPlayer.Memory;
import AroundPlayer.Player;

public class EvaluateTaskCommand implements Command{

    private Player player;

    public EvaluateTaskCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        if(player.getCurrentTask().isDone()){
            Memory memory = player.getCurrentTask().giveMemory();
            player.addMemory(memory);
            String temp = player.getCurrentTask().getName();
            player.deleteTask();
            player.getCurrentLocation().getFriendlyNPC().setTask(null);
            return "Splnil si úkol: " + temp + " a dostáváš novou vzpomínku: " + memory.getName();
        }
        return "Další předměty k odevzdání: " + player.getCurrentLocation().getFriendlyNPC().getTask().writeAllNeededItems();
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean waitAble() {
        return false;
    }
}
