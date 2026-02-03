package Commands.Locations;

import AroundPlayer.Player;
import Commands.Command;

/**
 * Command designed to write player's current location description
 */
public class ReadLocationDescriptionCommand implements Command {

    private final Player player;

    public ReadLocationDescriptionCommand(Player player) {
        this.player = player;
    }


    @Override
    public String execute() {
        return player.getCurrentLocation().getDescription();
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
