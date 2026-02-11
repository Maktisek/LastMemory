package Commands.GameCommands;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;
import Modes.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a command that switches the player's mode based on the provided input.
 * If the requested mode is not available or the location is not free, alternative actions may be triggered.
 * <p>
 * The mode is chosen through {@link #mode}.
 * @author Matěj Pospíšil
 */
public class SwitchModeCommand implements Command {

    private final HashMap<String, Mode> map;
    private final Player player;
    private String mode;

    public SwitchModeCommand(Player player) {
        this.player = player;
        this.map = new HashMap<>();
        fillMap();
    }

    /**
     * Loads the map with inputs as a keys and values as a modes.
     */
    public void fillMap() {
        map.put("lokace", new LocationMode());
        map.put("inventář", new BackpackMode());
    }

    @Override
    public String execute() {
        if (!map.containsKey(mode)) {
            Important.playSound("wrong sound");
            return Important.changeText("red", "Mód: " + mode + " neexistuje");
        }
        player.switchMode(map.get(mode));
        return Important.changeText("green", "Mód změněn na: " + mode);
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


    /**
     * Writes all modes, which can be chosen by the player.
     *
     * @return all the commands to be chosen.
     */
    private String writeKeys(){
        ArrayList<String> keys = new ArrayList<>();
        for (String key : map.keySet()){
            keys.add(Important.changeText("underline", key));
        }
        return String.join(", ", keys);
    }

    public String writeNamesOfModes() {
        return "Dostupné módy: " + writeKeys();
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public boolean isContinuing() {
        return true;
    }

    @Override
    public void endAudio() {

    }
}
