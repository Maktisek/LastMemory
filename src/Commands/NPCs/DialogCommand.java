package Commands.NPCs;

import AroundPlayer.Player;
import Commands.Command;
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
            String ascii = Important.readTxtFiles("res\\TextFiles\\asciiDialog.txt", 0);
            String headText = Important.asciiHeadTextHelper(player.getCurrentLocation().getFriendlyNPC().getCurrentSpeech(), ascii);
            return Important.writeSpace(40) + Important.changeText("bold", Important.changeText("pink", headText) + "\n" + player.getCurrentLocation().getFriendlyNPC().getCurrentSpeech());
        } else {
            this.inputWait = false;
            Important.playSound("wrong sound");
            return "V lokaci se nidko nenachází";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean isWaitAble() {
        return inputWait;
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
