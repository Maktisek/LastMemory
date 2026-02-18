package Commands.MovementCommands;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;

/**
 * Represents a command, which changes the player’s current location to the player’s previous location.
 * <p>
 *     If {@link Player#runAway()} action is not successful, then a message is returned indicating that the action failed.
 * </p>
 * Otherwise, the {@link Modes.QuestionMode} music is turned off and a message is returned indicating that the action was successful.
 * @author Matěj Pospíšil
 */
public class RunAwayCommand implements Command {

    private final Player player;


    public RunAwayCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        if (player.runAway()) {
            Important.stopMusic("question mode");
            Important.playSound("run");
            return Important.writeSpace(40)+Important.changeText("green", "Utíkáš zpět do: " + Important.changeText("underline",player.getCurrentLocation().writeName()));
        }
        Important.playSound("wrong sound");
        return Important.writeSpace(40)+Important.changeText("red", "Další útěk není možný");
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
