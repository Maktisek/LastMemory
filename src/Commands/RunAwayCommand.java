package Commands;

import AroundPlayer.Player;
import Game.Important;

/**
 * Command designed to exit locations with friendlyNPC.
 */
public class RunAwayCommand implements Command{

    private Player player;


    public RunAwayCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        if(player.getPreviousLocation() == null){
            return "Další útěk není možný";
        }
        Important.stopAudio(player.getCurrentLocation().getName());
        player.runAway();
        Important.playLocationSong(player.getCurrentLocation());
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
}
