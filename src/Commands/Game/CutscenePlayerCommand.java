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
    public boolean isWaitAble() {
        return true;
    }

    @Override
    public boolean IsTimeWaitAble() {
        return false;
    }

    @Override
    public boolean isContinuing() {
        return true;
    }

    @Override
    public void endAudio() {
        Important.stopMusic("cutscene");
    }
}
