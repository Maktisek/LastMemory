package Commands.GameCommands;

import AroundPlayer.Player;
import Commands.Command;
import Modes.LocationMode;

/**
 * Represents a command, which starts the game by switching the player’s mode to {@link LocationMode}.
 * <p>
 * The rest of the preparation is done in {@link Game.Console} and {@link Game.CommandLoader}.
 * @author Matěj Pospíšil
 */
public class StartGameCommand implements Command {

    private final Player player;

    public StartGameCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        player.setMode(new LocationMode());
        return "Užij si hru!";
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean isWaitAble() {
        return false;
    }

    @Override
    public boolean isTimeWaitAble() {
        return true;
    }

    @Override
    public boolean isContinuing() {
        return false;
    }

    @Override
    public void endAudio() {
    }

}
