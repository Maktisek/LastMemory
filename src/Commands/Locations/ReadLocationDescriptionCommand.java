package Commands.Locations;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;

/**
 * Command designed to write player's current location description
 */
public class ReadLocationDescriptionCommand implements Command {

    private final Player player;

    public ReadLocationDescriptionCommand(Player player) {
        this.player = player;
    }


    @Override
    public String execute() {
        String ascii = Important.readTxtFiles("res\\TextFiles\\asciiAbout.txt", 0);
        String headText = Important.asciiHeadTextHelper(player.getCurrentLocation().getDescription(), ascii);
        return Important.writeSpace(40) +Important.changeText("bold", Important.changeText("pink", headText) + "\n" + player.getCurrentLocation().getDescription());
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean isWaitAble() {
        return true;
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
