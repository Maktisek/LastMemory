package Commands;

import AroundPlayer.Player;

/**
 * Command designed to scan and add all possible locations into current location.
 */
public class ScanAndAddCommand implements Command{

    private Player player;

    public ScanAndAddCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        String result = player.scanAndAddPossibleLocations();
        if(result != null){
            return result;
        }
        return "Žádné nové lokace nebyly nalezeny";
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean waitAble() {
        return true;
    }
}
