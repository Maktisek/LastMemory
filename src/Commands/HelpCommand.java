package Commands;

import AroundPlayer.Player;

/**
 * Command designed to write all possible commands based on players mode.
 */
public class HelpCommand implements Command{

    private Player player;

    public HelpCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        return player.getMode().executeHelp(player);
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
}
