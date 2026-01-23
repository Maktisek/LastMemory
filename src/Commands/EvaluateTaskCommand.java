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
        if(!player.getCurrentTask().isDone()){
            return "Další předměty k odevzdání: " + player.getCurrentLocation().getFriendlyNPC().getTask().writeAllNeededItems();
        }
        Memory memory = player.getCurrentTask().giveMemory();
        player.addMemory(memory);
        String temp = player.getCurrentTask().getName();
        player.deleteTask();
        player.getCurrentLocation().getFriendlyNPC().setTask(null);
        player.getCurrentLocation().getFriendlyNPC().switchSpeeches();
        return "Splnil si úkol: " + temp + " a dostáváš novou vzpomínku: " + memory.getName();
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
