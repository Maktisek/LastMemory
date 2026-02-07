package Commands.Tasks;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;

public class ShowCurrentTaskCommand implements Command {

    private final Player player;

    public ShowCurrentTaskCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        if(player.getCurrentTask() != null){
            return player.getCurrentTask().toString();
        }
        Important.playSound("wrong sound");
        return Important.changeText("red", "Momentálně nemáš přijmutý žádný úkol");
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean isWaitAble() {
        return true;
    }

    @Override
    public boolean IsTimeWaitAble() {
        return false;
    }

    @Override
    public boolean isContinuing() {
        return true;
    }

    @Override
    public void endAudio() {

    }
}
