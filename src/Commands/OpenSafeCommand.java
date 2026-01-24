package Commands;

import AroundPlayer.Player;

public class OpenSafeCommand implements Command{

   private Player player;
   private String code;

    public OpenSafeCommand(Player player, String code) {
        this.player = player;
        this.code = code;
    }

    @Override
    public String execute() {
        if(player.getCurrentLocation().getSafe() != null && player.getCurrentLocation().getSafe().isLocked()){
           return player.getCurrentLocation().tryOpenSafe(code);
        }
        return "Safe se v lokaci nenachází";
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
