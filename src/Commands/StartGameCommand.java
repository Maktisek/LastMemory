package Commands;

import AroundPlayer.Player;
import Modes.LocationMode;

public class StartGameCommand implements Command{

    private final Player player;

    public StartGameCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        player.setMode(new LocationMode());
        return "Hra začíná...";
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
        return true;
    }

    @Override
    public boolean continuing() {
        return false;
    }

    @Override
    public void endAudio() {
    }

    public boolean endIntro(){
        return true;
    }
}
