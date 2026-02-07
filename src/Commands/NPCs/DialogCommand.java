package Commands.NPCs;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;

/**
 * Represents a command, which displays {@link NPCS.FriendlyNPC}’s current speech.
 * <p>
 *     The current speech is shown with an ascii art too. This ascii art is displayed via {@link Important#asciiHeadTextHelper(String, String)}.
 * </p>
 * If there is no {@link NPCS.FriendlyNPC} in player’s current location, then no dialog is shown, and
 * a message is returned indicating that no friendly NPC is available to talk to and {@link #inputWait} is set to false.
 * Otherwise, the current speech is displayed, and {@link #inputWait} remains true.
 * @author Matěj Pospíšil
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
            return Important.changeText("red", "V lokaci se nidko nenachází");
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
