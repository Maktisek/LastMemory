package Commands.SavesCommands;

import AroundPlayer.Player;
import Commands.Command;
import Exceptions.WrongInitializationException;
import Game.Important;
import Game.Initialization;
import Modes.LocationMode;

public class LoadGameCommand implements Command {


    private final Player player;
    private final String save;


    public LoadGameCommand(Player player, String save) {
        this.player = player;
        this.save = save;
    }

    @Override
    public String execute() {
        try {
            Initialization init = Initialization.readFromFile(save);
            Player loadedPlayer = init.getPlayer();
            this.player.load(loadedPlayer);
            this.player.setMode(new LocationMode());
        }catch (WrongInitializationException e){
            return Important.writeSpace(60)+Important.changeText("red", e.getMessage());
        }
        return Important.writeSpace(60)+Important.changeText("green", "Hra se načetla úspěšně!");
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
    public boolean isTimeWaitAble() {
        return false;
    }

    @Override
    public boolean isContinuing() {
        return true;
    }

    @Override
    public void endAudio() {

    }
}
