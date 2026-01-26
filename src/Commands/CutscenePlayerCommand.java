package Commands;

import AroundPlayer.Player;
import Game.Important;

public class CutscenePlayerCommand implements Command{


    private Player player;

    public CutscenePlayerCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        return player.getCutscenes().pollCutscene().toString();
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean waitAble() {
        return true;
    }

    @Override
    public boolean timeWaitAble() {
        return false;
    }

    @Override
    public boolean continuing() {
        return true;
    }
    @Override
    public void startAudio() {

    }

    @Override
    public void endAudio() {

    }
}
