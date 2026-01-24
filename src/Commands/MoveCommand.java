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

    public MoveCommand(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    @Override
    public String execute() {
        boolean action = player.switchLocation(player.getCurrentLocation().findLocation(this.name));
        if (!action) {
            return Important.changeText("red", "Lokace: " + this.name + " neexistuje");
        }

        if (player.getMode().getInfo().equalsIgnoreCase(new QuestionMode().getInfo())) {
            Important.stopAudio(player.getPreviousLocation().getName());
            Important.playAudio("question mode");
            return Important.changeText("green", "Přesouváš se do: " + name);
        }

        if (player.getCurrentLocation().getType() == Type.HALLWAY && player.getCurrentLocation().getType() == player.getPreviousLocation().getType()) {
            Important.playAudio("walk");
            Important.changeTitle(player.getPreviousLocation().getName(), player.getCurrentLocation().getName());
            return Important.changeText("green", "Přesouváš se do: " + name);
        }
        Important.stopAudio(player.getPreviousLocation().getName());
        Important.playAudio("walk");
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
        return true;
    }
}
