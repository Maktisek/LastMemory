package Commands.GameCommands;

import AroundPlayer.Player;
import Commands.Command;

/**
 * Represents a command, which writes all possible commands based on players mode.
 * @author Matěj Pospíšil
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
    public boolean isWaitAble() {
        return true;
    }

    @Override
    public boolean isTimeWaitAble() {
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
