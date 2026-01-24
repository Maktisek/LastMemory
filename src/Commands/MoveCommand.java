package Commands;

import AroundPlayer.Player;
import AudioSystem.AudioLibrary;
import Game.Important;

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
        if(player.switchLocation(player.getCurrentLocation().findLocation(this.name))){
            Important.stopAudio(player.getPreviousLocation().getName());
            Important.playAudio("walk");
            Important.playLocationSong(player.getCurrentLocation());
            return "Přesouváš se do: " + name;
        }else {
            return "Lokace: " + this.name + " neexistuje";
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
}
