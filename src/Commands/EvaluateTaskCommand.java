package Commands;

import AroundPlayer.Memory;
import AroundPlayer.Player;
import Game.Important;

public class EvaluateTaskCommand implements Command{

    private final Player player;

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
        player.deleteTask();
        player.getCurrentLocation().getFriendlyNPC().setTask(null);
        player.getCurrentLocation().getFriendlyNPC().switchSpeeches();
        player.getCurrentLocation().pauseMusic();
        Important.playSound("new memory");
        return Important.writeSpace(40)+"Ou, myslím, že si na něco vzpomínám...";
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
        Important.stopSound("new memory");
    }
}
