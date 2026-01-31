package Commands;

import AroundPlayer.Player;
import Game.Important;

/**
 * Command designed to exit locations with friendlyNPC.
 */
public class RunAwayCommand implements Command{

    private final Player player;


    public RunAwayCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        if(player.getPreviousLocation() == null){
            Important.playSound("wrong sound");
            return "Další útěk není možný";
        }
        Important.stopMusic("question mode");
        player.runAway();
        return "Utíkáš zpět do: " + player.getCurrentLocation().getName();

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

    @Override
    public boolean continuing() {
        return true;
    }

    @Override
    public void endAudio() {

    }
}
