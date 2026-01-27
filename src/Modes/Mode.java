package Modes;

import AroundPlayer.Player;

/**
 * This interface helps to avoid thousands of ifs in class Console. It uses strategy pattern in order to change player modes.
 * The modes are based on where the player is and which mode he wants.
 */
public interface Mode {

    /**
     * Writes the information based on the mode. Every mode writes his own information. For instance
     * BackpackMode writes information about backpack.
     * @param player the player whose information will be written
     * @return information
     */
    String executeInfo(Player player);

    /**
     * Shows all command, which can be done in the specific mode
     * @return the commands which can be done
     */
    String executeHelp();

    /**
     * Works such as "identifier"
     * @return the name of the mode
     */
    String getInfo();

    boolean special();





}
