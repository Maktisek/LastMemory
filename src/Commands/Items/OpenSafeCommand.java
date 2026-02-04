package Commands.Items;

import AroundPlayer.Player;
import Commands.Command;
import Exceptions.WrongSafeCodeException;
import Game.Important;

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
    public boolean waitAble() {
        return true;
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
    public void endAudio() {

    }
}
