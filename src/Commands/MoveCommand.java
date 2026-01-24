package Commands;

import AroundPlayer.Player;
import AudioSystem.AudioLibrary;

/**
 * Command designed to change the player's currentLocation.
 */
public class MoveCommand implements Command{

    private Player player;
    private String name;
    private AudioLibrary audioLibrary;

    public MoveCommand(Player player, String name, AudioLibrary audioLibrary) {
        this.player = player;
        this.name = name;
        this.audioLibrary = audioLibrary;
    }

    @Override
    public String execute() {
        if(player.switchLocation(player.getCurrentLocation().findLocation(this.name))){
            audioLibrary.playAudio("walk");
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
