package Commands;

import AroundPlayer.Player;

public class HandInCommand implements Command{

    private Player player;

    public HandInCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        return "";
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
