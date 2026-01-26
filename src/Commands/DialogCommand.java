package Commands;

import AroundPlayer.Player;
import Game.Important;

/**
 * Command designed to write current location’s friendlyNPC current speech
 */
public class DialogCommand implements Command {

    private final Player player;
    private boolean inputWait;

    public DialogCommand(Player player) {
        this.player = player;
        this.inputWait = true;
    }

    @Override
    public String execute() {
        if (player.getCurrentLocation().getFriendlyNPC() != null) {
            return Important.writeSpace(25)+Important.writeLongTexts(player.getCurrentLocation().getFriendlyNPC().getCurrentSpeech());
        } else {
            this.inputWait = false;
            return "V lokaci se nidko nenachází";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean waitAble() {
        return inputWait;
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
    public void startAudio() {

    }

    @Override
    public void endAudio() {

    }
}
