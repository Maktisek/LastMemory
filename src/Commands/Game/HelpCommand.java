package Commands.Game;

import AroundPlayer.Player;
import Commands.Command;

/**
 * Command designed to write all possible commands based on players mode.
 */
public class HelpCommand implements Command {

    private final Player player;

    public HelpCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        return player.getMode().executeHelp();
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
