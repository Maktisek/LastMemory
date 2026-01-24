package Commands;

import AroundPlayer.Memory;
import AroundPlayer.Player;
import Game.Important;

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
        Important.playAudio("new memory");
        return "Ou, myslím, že si na něco vzpomínám...";
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
}
