package Commands.Tasks;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;


public class InspectOldTaskCommand implements Command {

    private final Player player;
    private final String name;

    public InspectOldTaskCommand(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    @Override
    public String execute() {
        if(player.hasOldTask(name)){
            return player.writeOldTask(name);
        }
        Important.playSound("wrong sound");
        return Important.changeText("red", "Úkol " + name + " neexistuje");
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
    public boolean isTimeWaitAble() {
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
