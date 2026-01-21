package Commands;

import AroundPlayer.Player;

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
}
