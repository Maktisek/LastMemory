package Commands;

import AroundPlayer.Player;

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
        if(player.runAway()){
            return "Utíkáš zpět do: " + player.getCurrentLocation().getName();
        }
        return "Další útěk není možný";
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
