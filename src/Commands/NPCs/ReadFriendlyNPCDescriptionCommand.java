package Commands.NPCs;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;

public class ReadFriendlyNPCDescriptionCommand implements Command {

    private final Player player;

    public ReadFriendlyNPCDescriptionCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        if(player.getCurrentLocation().getFriendlyNPC() == null){
            Important.playSound("wrong sound");
            return "V lokaci se nikdo nenachází";
        }
        return player.getCurrentLocation().getFriendlyNPC().writeDescription();
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
