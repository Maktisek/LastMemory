package Commands.SavesCommands;

import AroundPlayer.Player;
import Commands.Command;
import Exceptions.WrongInitializationException;
import Game.Important;
import Game.Initialization;

/**
 * Represents a command, which saves the game into {@code user.home - LastMemorySaves} file.
 * <p>
 * {@link #save} represents the save to which the game will be saved
 * </p>
 * The game cannot be saved if:
 * <ul>
 *     <li>{@link #save} matches {@code ""*} regex</li>
 *     <li>a {@link WrongInitializationException} occurs while saving the game</li>
 * </ul>
 * Otherwise, the game is saved into {@link #save} and the game is saved.
 * @author Matěj Pospíšil
 */
public class SaveGameCommand implements Command {


    private final Initialization init;
    private final String save;
    private final Player player;

    public SaveGameCommand(Initialization init, String save, Player player) {
        this.init = init;
        this.save = save;
        this.player = player;
    }

    @Override
    public String execute() {
        if(save.matches(" *")){
            Important.playSound("wrong sound");
            return Important.writeSpace(60)+Important.changeText("red", "Čisté mezery nemohou být názvem savu");
        }
        try {
            init.writeToFile(save);
            this.player.setSave(save);
        } catch (WrongInitializationException e) {
            Important.playSound("wrong sound");
            return Important.writeSpace(60)+Important.changeText("red", "Save " + save + " neexistuje");
        }
        Important.playSound("system");
        return Important.writeSpace(60)+Important.changeText("green" ,"Hra byla úspěšně uložena do " + save);
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
        return false;
    }

    @Override
    public void endAudio() {

    }
}
