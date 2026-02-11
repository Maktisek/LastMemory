package Commands.ItemsCommands;

import AroundPlayer.Player;
import Commands.Command;
import Exceptions.WrongSafeCodeException;
import Game.Important;

/**
 * Represents a command, which opens a safe.
 * <p>
 * {@link #code} represents a code of the safe to be opened.
 * <p>
 * If the code is not written by its own regex or the code is invalid, then no action is performed. Only the information about
 * the process is returned.
 * Otherwise, the safe is opened and the contents are added to the player’s current location.
 * @author Matěj Pospíšil
 */
public class OpenSafeCommand implements Command {

    private final Player player;
    private final String code;

    public OpenSafeCommand(Player player, String code) {
        this.player = player;
        this.code = code;
    }

    @Override
    public String execute() {
        if (player.getCurrentLocation().getSafe() == null || !player.getCurrentLocation().getSafe().isLocked()) {
            Important.playSound("wrong sound");
            return Important.changeText("red", "Safe se v lokaci nenachází");
        }
        try {
            if (player.getCurrentLocation().getSafe().openSafe(code)) {
                Important.playSound("safe open");
                return player.getCurrentLocation().openSafe();
            }
        } catch (WrongSafeCodeException e) {
            Important.playSound("wrong sound");
            return Important.changeText("red", e.getMessage());
        }
        Important.playSound("wrong sound");
        return Important.changeText("red", "Jajchs! Tenhle kód nefunguje");
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean isWaitAble() {
        return true;
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
