package Commands.Movement;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;

/**
 * Command designed to exit locations with friendlyNPC.
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
            return Important.changeText("green", "Utíkáš zpět do: " + Important.changeText("underline",player.getCurrentLocation().writeName()));
        }
        Important.playSound("wrong sound");
        return Important.changeText("red", "Další útěk není možný");
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
