package Commands.Tasks;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;

public class HandInCommand implements Command {

    private final Player player;
    private boolean continues;

    public HandInCommand(Player player) {
        this.player = player;
        this.continues = true;
    }

    @Override
    public String execute() {
        if(player.getCurrentTask() == null){
            Important.playSound("wrong sound");
            continues = false;
            return Important.changeText("red", "Momentálně nemáš žádný aktivní úkol");
        }
        if(player.getCurrentLocation().getFriendlyNPC() == null){
            Important.playSound("wrong sound");
            continues = false;
            return Important.changeText("red", "Tady není komu odevzdávat předměty");
        }
        if(player.getCurrentLocation().getFriendlyNPC().getTask() == null){
            Important.playSound("wrong sound");
            continues = false;
            return Important.changeText("red", "U "+player.getCurrentLocation().getFriendlyNPC().getName() + " nemáš žádný aktivní úkol.");
        }
        if(!player.getCurrentTask().getName().equalsIgnoreCase(player.getCurrentLocation().getFriendlyNPC().getTask().getName())){
            Important.playSound("wrong sound");
            continues = false;
            return Important.changeText("red", player.getCurrentLocation().getFriendlyNPC().getName() + " ti nezadal " + player.getCurrentTask().getName());
        }
        if(!player.getCurrentTask().canSolve(player)){
            Important.playSound("wrong sound");
            continues = false;
            return Important.changeText("red", "Nelze odevzdat žádný předmět");
        }
        String result = player.getCurrentTask().scanAndSolveTask(player);
        player.getCurrentLocation().getFriendlyNPC().setTask(player.getCurrentTask());
        Important.playSound("hand in");
        return result;
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
        return true;
    }

    @Override
    public boolean continuing() {
        return continues;
    }

    @Override
    public void endAudio() {

    }
}
