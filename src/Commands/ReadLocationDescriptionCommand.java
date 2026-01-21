package Commands;

import AroundPlayer.Player;

public class ReadLocationDescriptionCommand implements Command{

    Player player;

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
}
