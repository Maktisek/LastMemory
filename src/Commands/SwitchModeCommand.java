package Commands;

import AroundPlayer.Player;
import Modes.BackpackMode;
import Modes.LocationMode;
import Modes.Mode;

import java.util.HashMap;

public class SwitchModeCommand implements Command{

    private HashMap<String, Mode> map;
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

    public void fillMap(){
        map.put("lokace", new LocationMode());
        map.put("inventář", new BackpackMode());
    }

    @Override
    public String execute() {
        if (map.containsKey(mode)) {
            player.switchMode(map.get(mode));
            return "Mód změněn na: " + mode;
        }
        return "Mód: " + mode + " neexistuje";
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean waitAble() {
        return false;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String writeNamesOfModes(){
        return "Dostupné módy: "+String.join(", ", map.keySet());
    }
}
