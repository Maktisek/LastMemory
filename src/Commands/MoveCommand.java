package Commands;

import AroundPlayer.Player;
import Game.Important;
import Locations.Type;
import Modes.QuestionMode;

/**
 * Command designed to change the player's currentLocation.
 */
public class MoveCommand implements Command {

    private final Player player;
    private final String name;
    private boolean continues;

    public MoveCommand(Player player, String name) {
        this.player = player;
        this.name = name;
        this.continues = true;
    }

    @Override
    public String execute() {
        boolean action = player.switchLocation(player.getCurrentLocation().findLocation(this.name));
        if (!action) {
            this.continues = false;
            Important.playSound("wrong sound");
            return Important.changeText("red", "Lokace: " + this.name + " neexistuje");
        }

        if (player.getMode().getInfo().equalsIgnoreCase(new QuestionMode().getInfo())) {
            player.getPreviousLocation().pauseMusic();
            Important.playMusic("question mode");
            return Important.changeText("green", "Přesouváš se do: " + name);
        }

        if (player.getCurrentLocation().getType() == Type.HALLWAY && player.getCurrentLocation().getType() == player.getPreviousLocation().getType()) {
            Important.playSound("walk");
            player.getCurrentLocation().setSong(player.getPreviousLocation().getSong());
            return Important.changeText("green", "Přesouváš se do: " + name);
        }

        if (player.getCurrentLocation().getType() == Type.FADE && player.getCurrentLocation().getType() == player.getPreviousLocation().getType()) {
            player.getPreviousLocation().stopMusic();
            Important.playSound("walk");
            player.getCurrentLocation().playMusic(player.getPreviousLocation().getSong().getClip().getMicrosecondPosition());
            return Important.changeText("green", "Přesouváš se do: " + name);
        }

        player.getPreviousLocation().pauseMusic();
        Important.playSound("walk");
        if (player.getCurrentLocation().getSong().getClip() != null) {
            player.getCurrentLocation().resumeMusic();
        } else {
            player.getCurrentLocation().playMusic(0);
        }
        return Important.changeText("green", "Přesouváš se do: " + name);
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
        return true;
    }

    @Override
    public boolean continuing() {
        return continues;
    }

    @Override
    public void endAudio() {

    }
}
