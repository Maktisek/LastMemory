package Commands;

import AroundPlayer.Player;

/**
 * Command designed to scan and add all possible locations into current location.
 */
public class ScanAndAddCommand implements Command{

    private Player player;
    private boolean wait;

    public ScanAndAddCommand(Player player) {
        this.player = player;
        this.wait = true;
    }

    @Override
    public String execute() {
        String result = player.scanAndAddPossibleLocations();
        if(result != null){
            return result;
        }
        wait = false;
        return "Žádné nové lokace nebyly nalezeny";
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean waitAble() {
        return wait;
    }
}
