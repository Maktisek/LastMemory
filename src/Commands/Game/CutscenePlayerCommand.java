package Commands.Game;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;

public class CutscenePlayerCommand implements Command {


    private final Player player;

    public CutscenePlayerCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        player.getCurrentLocation().pauseMusic();
        Important.playSound("fade cutscene");
        Important.playMusic("cutscene");
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
    public void endAudio() {
        Important.stopMusic("cutscene");
    }
}
