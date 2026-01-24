package Commands;

import AroundPlayer.Player;
import Game.Important;
import Locations.Type;

/**
 * Command designed to change the player's currentLocation.
 */
public class MoveCommand implements Command{

    private Player player;
    private String name;

    public MoveCommand(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    @Override
    public String execute() {
        boolean action = player.switchLocation(player.getCurrentLocation().findLocation(this.name));
        if (!action) {
            return "Lokace: " + this.name + " neexistuje";
        }
        if (player.getCurrentLocation().getType() == Type.HALLWAY && player.getCurrentLocation().getType() == player.getPreviousLocation().getType()) {
            Important.playAudio("walk");
            Important.changeTitle(player.getPreviousLocation().getName(), player.getCurrentLocation().getName());
            return "Přesouváš se do: " + name;
        }
        Important.stopAudio(player.getPreviousLocation().getName());
        Important.playAudio("walk");
        Important.playLocationSong(player.getCurrentLocation());
        return "Přesouváš se do: " + name;
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
