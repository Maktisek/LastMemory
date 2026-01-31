package Commands;

import AroundPlayer.Player;
import Game.Important;
import Modes.BackpackMode;
import Modes.LocationMode;
import Modes.Mode;
import Modes.QuestionMode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * More complex command designed to change player's mode
 */
public class SwitchModeCommand implements Command {

    private final HashMap<String, Mode> map;
    private Player player;
    private String mode;

    public SwitchModeCommand(String mode, Player player) {
        this.map = new HashMap<>();
        this.mode = mode;
        this.player = player;
        fillMap();
    }

    public SwitchModeCommand() {
        this.map = new HashMap<>();
        fillMap();
    }

    /**
     * Loads the map with inputs as a keys and values as a modes
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

        if (map.get(mode).getInfo().equalsIgnoreCase(new LocationMode().getInfo()) && !player.getCurrentLocation().isFree()) {
            player.switchMode(new QuestionMode());
            return Important.changeText("green", "Mód změněn na: " + mode);
        }

        player.switchMode(map.get(mode));
        return Important.changeText("green", "Mód změněn na: " + mode);
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

    public void setMode(String mode) {
        this.mode = mode;
    }

    private String keys(){
        ArrayList<String> keys = new ArrayList<>();
        for (String key : map.keySet()){
            keys.add(Important.changeText("underline", key));
        }
        return String.join(", ", keys);
    }


    /**
     * Writes all map keys
     *
     * @return map keys
     */
    public String writeNamesOfModes() {
        return "Dostupné módy: " + keys();
    }

    @Override
    public boolean continuing() {
        return true;
    }

    @Override
    public void endAudio() {

    }
}
