package Commands.NPCsCommands;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;

/**
 * Represents a command, which displays {@link NPCS.FriendlyNPC} description.
 * <p>
 *     If there is no friendly NPC in player’s current location, then a message is returned
 *     indicating that no friendly NPC is available to show a description.
 *     Otherwise, the description is displayed to the player.
 * </p>
 * @author Matěj Pospíšil
 */
public class ReadFriendlyNPCDescriptionCommand implements Command {

    private final Player player;

    public ReadFriendlyNPCDescriptionCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        if(player.getCurrentLocation().getFriendlyNPC() == null){
            Important.playSound("wrong sound");
            return Important.writeSpace(40)+Important.changeText("red", "V lokaci se nikdo nenachází");
        }
        return Important.writeSpace(40)+player.getCurrentLocation().getFriendlyNPC().writeDescription();
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
