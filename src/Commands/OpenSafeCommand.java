package Commands;

import AroundPlayer.Player;
import Game.Important;

public class OpenSafeCommand implements Command{

   private final Player player;
   private final String code;

    public OpenSafeCommand(Player player, String code) {
        this.player = player;
        this.code = code;
    }

    @Override
    public String execute() {
        if(player.getCurrentLocation().getSafe() == null || !player.getCurrentLocation().getSafe().isLocked()){
            return Important.changeText("red", "Safe se v lokaci nenachází");
        }

        if(!player.getCurrentLocation().getSafe().openSafe(code)){
            return "Kód " + code + " není správný, či srávně zapsaný";
        }
        Important.playSound("safe open");
        return player.getCurrentLocation().openSafe();
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

    }
}
