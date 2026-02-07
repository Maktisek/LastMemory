package Commands.Movement;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;

/**
 * Represents a command, which uses {@link Player#scanAndAddPossibleLocations()} to add new locations into player’s current location.
 * <p>
 *     This command always runs after {@link MoveCommand} is successfully done.
 * </p>
 * If no new locations are added, then a message is returned indicating this result.
 * Otherwise, a message is returned indicating, which locations has been added.
 * @author Matěj Pospíšil
 */
public class ScanAndAddCommand implements Command {

    private final Player player;
    private boolean waitInput;
    private boolean waitTime;

    public ScanAndAddCommand(Player player) {
        this.player = player;
        this.waitInput = true;
        this.waitTime = false;
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
        waitTime = true;
        return Important.changeText("red", "Žádné nové lokace nebyly nalezeny");
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean isWaitAble() {
        return waitInput;
    }

    @Override
    public boolean isTimeWaitAble() {
        return waitTime;
    }

    @Override
    public boolean isContinuing() {
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
