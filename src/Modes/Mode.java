package Modes;

import AroundPlayer.Player;

/**
 * Defines a mode, which is attached to a player.
 * <p>
 *     This mode is then used in order to make the game behave differently.
 * </p>
 * @author Matěj Pospíšil
 */
public interface Mode {

    /**
     * Writes the information based on the mode. Every mode writes its own information. For instance
     * {@link BackpackMode} writes information about player’s inventory.
     * @param player the player whose information will be displayed
     * @return the information about the player
     */
    String executeInfo(Player player);

    /**
     * Shows all commands, which can be done in the specific mode
     * @return the commands which can be done
     */
    String executeHelp();

    /**
     * Works such as "identifier", which is used to match modes together.
     * @return the name of the mode
     */
    ModeType getInfo();

    /**
     * Works as an identifier of whether the mode is special.
     * <p>
     * When the mode is special, some commands can be executed in this mode
     * while they may also be available in other modes, but not in all modes
     * at the same time.
     * </p>
     * @return true if the mode is special, false otherwise
     */

    boolean isSpecial();
}
