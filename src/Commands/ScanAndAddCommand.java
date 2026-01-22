package Commands;

import AroundPlayer.Player;

public class ScanAndAddCommand implements Command{

    private Player player;

    public ScanAndAddCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        return player.scanAndAddPossibleLocations();
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean waitAble() {
        return true;
    }
}
