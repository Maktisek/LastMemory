package Commands;

import AroundPlayer.Player;
import Game.Important;

public class DialogCommand implements Command{

    private Player player;

    public DialogCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        return Important.writeLongTexts(player.getCurrentLocation().writeSpeech());
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
