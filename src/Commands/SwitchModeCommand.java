package Commands;

import AroundPlayer.Player;
import Modes.Mode;

import java.util.HashMap;

public class SwitchModeCommand implements Command{

    public HashMap<String, Mode> map;
    public Player player;
    public String mode;

    public SwitchModeCommand(String mode, Player player) {
        this.map = new HashMap<>();
        this.mode = mode;
        this.player = player;
    }

    public void fillMap(){
        //TODO fillMap metoda chybi
        //Naplni mapu klicovymi slovy a jejich mody
    }

    @Override
    public String execute() {
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
