package Commands;

import AroundPlayer.Player;
import Game.Important;

public class ReadLocationDescriptionCommand implements Command{

    Player player;

    public ReadLocationDescriptionCommand(Player player) {
        this.player = player;
    }


    @Override
    public String execute() {
        return Important.writeLongTexts(player.getCurrentLocation().getDescription());
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
