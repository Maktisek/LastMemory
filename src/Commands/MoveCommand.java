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
            return Important.changeText("red", "Lokace: " + this.name + " neexistuje");
        }

        if (player.getMode().getInfo().equalsIgnoreCase(new QuestionMode().getInfo())) {
            Important.stopMusic(player.getPreviousLocation().getName());
            Important.playMusic("question mode");
            return Important.changeText("green", "Přesouváš se do: " + name);
        }

        if (player.getCurrentLocation().getType() == Type.HALLWAY && player.getCurrentLocation().getType() == player.getPreviousLocation().getType()) {
            Important.playSound("walk");
            Important.changeTitle(player.getPreviousLocation().getName(), player.getCurrentLocation().getName());
            return Important.changeText("green", "Přesouváš se do: " + name);
        }
        Important.stopMusic(player.getPreviousLocation().getName());
        Important.playSound("walk");
        Important.playLocationSong(player.getCurrentLocation());
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
