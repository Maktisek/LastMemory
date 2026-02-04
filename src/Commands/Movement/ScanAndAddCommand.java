package Commands.Movement;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;

/**
 * Command designed to scan and add all possible locations into current location.
 */
public class ScanAndAddCommand implements Command {

    private final Player player;
    private boolean waitInput;

    public ScanAndAddCommand(Player player) {
        this.player = player;
        this.waitInput = true;
    }

    @Override
    public String execute() {
        String result = player.scanAndAddPossibleLocations();
        if(result != null){
            player.getCurrentLocation().pauseMusic();
            Important.playSound("new location");
            return result;
        }
        waitInput = false;
        return Important.changeText("red", "Žádné nové lokace nebyly nalezeny");
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean waitAble() {
        return waitInput;
    }

    @Override
    public boolean timeWaitAble() {
        return true;
    }

    @Override
    public boolean continuing() {
        return true;
    }

    @Override
    public void endAudio() {
        if (player.getCurrentLocation().getSongClip() != null) {
            player.getCurrentLocation().resumeMusic();
        } else {
            player.getCurrentLocation().playMusic(0);
        }
    }
}
