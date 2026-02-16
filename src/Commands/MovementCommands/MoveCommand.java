package Commands.MovementCommands;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;
import Locations.LocationType;
import Modes.ModeType;
import Modes.QuestionMode;

/**
 * Represents a command, which changes the player’s current location.
 * <p>
 *     {@link #name} represents the name of the location, to which the player will be moved.
 * </p>
 * If the desired location does not exist, or it is not connected with player’s current location,
 * then a message is returned indicating that the movement is not possible and {@link #continues} is set to false.
 * Otherwise, the player’s current location changes.
 * <p>
 *     Player’s mode can change to {@link QuestionMode} after moving into a location featuring an enemy NPC.
 * </p>
 * If the player’s mode is {@link QuestionMode}, then a question mode’s music is played instead of location’s music.
 * <p>
 *     If the previous and new location are both having their type set to {@link LocationType#HALLWAY}, then the music is not switched.
 * </p>
 * If the previous and new location are both having their type set to {@link LocationType#FADE}, then the music of the new location is
 * starting from where the previous location’s music ended.
 * @author Matěj Pospíšil
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

        if (player.getMode().getInfo() == ModeType.question) {
            player.getPreviousLocation().pauseMusic();
            Important.playMusic("question mode");
            return Important.changeText("green", "Přesouváš se do: " + name);
        }

        if (player.getCurrentLocation().getType() == LocationType.HALLWAY && player.getCurrentLocation().getType() == player.getPreviousLocation().getType()) {
            Important.playSound("walk");
            return Important.changeText("green", "Přesouváš se do: " + name);
        }

        if (player.getCurrentLocation().getType() == LocationType.FADE && player.getCurrentLocation().getType() == player.getPreviousLocation().getType()) {
            player.getPreviousLocation().stopMusic();
            Important.playSound("walk");
            player.getCurrentLocation().playMusic(player.getPreviousLocation().getSong().getClip().getMicrosecondPosition());
            return Important.changeText("green", "Přesouváš se do: " + name);
        }

        if(player.getCurrentLocation().getType() == LocationType.HALLWAY && player.getPreviousLocation().getType() == LocationType.ELEVATOR){
            player.getPreviousLocation().pauseMusic();
            Important.playSound("elevator walk");
            return Important.changeText("green", "Přesouváš se do: " + name);
        }

        player.getPreviousLocation().pauseMusic();
        if(player.getCurrentLocation().getType() == LocationType.ELEVATOR){
            Important.playSound("elevator walk");
        }else {
            Important.playSound("walk");
        }

        return Important.changeText("green", "Přesouváš se do: " + name);
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean isWaitAble() {
        return false;
    }

    @Override
    public boolean isTimeWaitAble() {
        return true;
    }

    @Override
    public boolean isContinuing() {
        return continues;
    }

    @Override
    public void endAudio() {

    }
}
