package Commands.SavesCommands;

import AroundPlayer.Player;
import Commands.Command;
import Exceptions.WrongInitializationException;
import Game.Important;
import Game.Initialization;
import Modes.LocationMode;
import Modes.ModeType;

/**
 * Represents a command, which loads the game save from {@code user.home - LastMemorySaves}.
 * <p>
 * {@link #save} represents the save from which the game will be loaded
 * </p>
 * The game cannot be loaded if:
 * <ul>
 *     <li>players mode is set to {@link Modes.IntroMode} and {@link #save} is null at the same time</li>
 *     <li>{@link #save} is null</li>
 *     <li>if a {@link WrongInitializationException} occurs while loading the game</li>
 * </ul>
 * Otherwise, {@link #player} is rewritten from a new instance of {@link Player} created out of new instance of {@link Initialization} via
 * {@link Initialization#readFromFile(String)}. After all player’s mode has to be set to {@link LocationMode} and
 * @author Matěj Pospíšil
 */
public class LoadGameCommand implements Command {


    private final Player player;
    private final String save;
    private boolean isWaitAble;


    public LoadGameCommand(Player player, String save) {
        this.player = player;
        this.save = save;
        this.isWaitAble = true;
    }

    @Override
    public String execute() {

        if (player.getMode().getInfo() == ModeType.intro && save == null) {
            Important.playSound("wrong sound");
            this.isWaitAble = false;
            return Important.writeSpace(60) + Important.changeText("red", "Akci " + Important.changeText("underline", "načíst hru") + Important.changeText("red", " nelze nyní provést"));
        }
        if (save == null) {
            Important.playSound("wrong sound");
            return Important.writeSpace(60) + Important.changeText("red", "Nyní není k dispozici žádný save");
        }

        try {
            Initialization init = Initialization.readFromFile(save);
            player.getCurrentLocation().stopMusic();
            init.setAllMusic();
            Player loadedPlayer = init.getPlayer();
            this.player.load(loadedPlayer);
            this.player.setMode(new LocationMode());
            this.player.setSave(save);
        } catch (WrongInitializationException e) {
            Important.playSound("wrong sound");
            return Important.writeSpace(60) + Important.changeText("red", e.getMessage());
        }
        Important.playSound("system");
        return Important.writeSpace(60) + Important.changeText("green", "Hra se načetla úspěšně!");
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean isWaitAble() {
        return isWaitAble;
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
