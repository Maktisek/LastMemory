package Commands;

import AroundPlayer.Player;
import Game.Important;

/**
 * Command designed to write current location’s friendlyNPC current speech
 */
public class DialogCommand implements Command {

    private Player player;

    public DialogCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        if (player.getCurrentLocation().getFriendlyNPC() != null) {
            return Important.writeLongTexts(player.getCurrentLocation().getFriendlyNPC().getCurrentSpeech());
        } else {
            return "V lokaci se nidko nenachází";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean waitAble() {
        return false;
    }

    @Override
    public boolean timeWaitAble() {
        return false;
    }
}
