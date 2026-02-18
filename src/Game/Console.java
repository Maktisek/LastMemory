package Game;

import AroundPlayer.Player;
import Commands.*;
import Commands.GameCommands.CutscenePlayerCommand;
import Commands.GameCommands.ExitCommand;
import Commands.GameCommands.StartGameCommand;
import Exceptions.WrongInitializationException;
import Modes.Mode;
import Modes.ModeType;
import Modes.OutroMode;

import java.util.*;

/**
 * Handles the entire game loop.
 * <p>
 * The game is:
 * <ul>
 *     <li>initialized here</li>
 *     <li>played from here</li>
 *     <li>ended here</li>
 * </ul>
 * </p>
 * <p>
 * {@link #exit} determines whether the game should stop.
 * It is initially set to {@code false}. When changed to {@code true},
 * the game loop terminates.
 *
 * <p>
 * {@link #player} must be loaded via {@link Initialization#getPlayer()}.
 * </p>
 *
 * @author Matěj Pospíšil
 */
public class Console {

    private final Player player;
    private final CommandLoader gameLoader;
    private boolean exit;

    /**
     * Sets up the whole game using {@link Initialization}.
     *
     * @throws WrongInitializationException if the initialization process fails.
     */
    public Console() throws WrongInitializationException {
        this.exit = false;
        Initialization init = new Initialization();
        init.startInitialization();
        this.player = init.getPlayer();
        this.gameLoader = new CommandLoader(player);
    }

    public void start() {
        executeIntro();
    }

    /**
     * This method stands for pre execute process, in which the player chooses what he wants to do.
     * <p>
     * It works as a start of a new loop.
     * </p>
     * On the start of the loop those actions can be done:
     * <ul>
     *     <li>The cutscene can be played through {@link #cutscenePlayer()}</li>
     *     <li>The game can end and proceed to {@link #executeOutro()}</li>
     *     <li>The player’s current location’s music can be resumed or played</li>
     * </ul>
     * If cutscene cannot be played and game cannot be ended yet, the player chooses the command he wants to execute.
     */
    private void preExecute() {
        System.out.println(Important.writeSpace(60));
        while (!exit) {
            String command;

            if (player.canPlayCutscene()) {
                cutscenePlayer();
                continue;
            }

            if (player.canEnd()) {
                exit = true;
                player.setMode(new OutroMode());
                player.getCurrentLocation().pauseMusic();
                executeOutro();
                continue;
            }

            if (player.getMode().getInfo() != ModeType.question) {
                if (player.getCurrentLocation().getSongClip() != null) {
                    player.getCurrentLocation().getSong().resume();
                } else {
                    player.getCurrentLocation().playMusic(0);
                }
            }

            System.out.println(player);
            System.out.print(">> ");
            command = Important.loadText();
            if (checkCommand(command)) {
                execute(gameLoader.getCommands().get(command).get());
            }

        }
    }

    /**
     * This method stands for executing commands one after another.
     * <p>
     * Every command decides if:
     * </p>
     * <ul>
     *     <li>the console should wait for the player’s input through {@link #waitUntilInput(Command)}</li>
     *     <li>the console should wait through {@link #waitUntilTime(Command)}</li>
     *     <li>another command should be executed afterwards through {@link Command#isContinuing()} - some commands
     *     may tend to stop the chain</li>
     * </ul>
     *
     * @param listOfCommands stands for a list filled by all commands which have to be executed
     */
    private void execute(List<Command> listOfCommands) {
        for (Command currentCommand : listOfCommands) {
            System.out.println(currentCommand.execute());
            this.exit = currentCommand.exit();
            waitUntilInput(currentCommand);
            waitUntilTime(currentCommand);
            if (!currentCommand.isContinuing() || this.exit) {
                break;
            }
        }
    }

    /**
     * This method is responsible for checking if the chain of commands can be executed.
     * <p>
     * The problem can occur in those situations:
     *     <ul>
     *         <li>when the command does not exist in {@link CommandLoader#getCommands()}</li>
     *         <li>when the command’s mode does not match with the player’s mode</li>
     *     </ul>
     * </p>
     *
     * @param command the command keyword to be checked
     * @return true if the chain can be executed, otherwise false
     */
    private boolean checkCommand(String command) {
        if (!gameLoader.getCommands().containsKey(command.toLowerCase())) {
            Important.playSound("wrong sound");
            System.out.println(Important.writeSpace(40)+Important.changeText("red", "Akce " + Important.changeText("underline", command) + Important.changeText("red", " neexistuje")));
            return false;
        }
        Mode foundMode = gameLoader.getPossibleCommands().get(command).get();
        if (foundMode != null && player.getMode().getInfo() != foundMode.getInfo()) {
            Important.playSound("wrong sound");
            System.out.println(Important.writeSpace(40)+Important.changeText("red", "Akci " + Important.changeText("underline", command) + Important.changeText("red", " nelze nyní provést")));
            return false;
        }
        return true;
    }

    /**
     * Displays a cutscene through {@link CutscenePlayerCommand}.
     * <p>
     * This method can be called from {@link #preExecute()} and only if the player is capable of playing
     * another cutscene.
     * </p>
     */
    private void cutscenePlayer() {
        player.getCurrentLocation().pauseMusic();
        Command command = new CutscenePlayerCommand(player);
        System.out.println(command.execute());
        waitUntilInput(command);
        System.out.println(Important.writeSpace(60));
    }

    /**
     * Waits until player’s input.
     * <p>
     * If the player cannot play a cutscene now, then the player’s current location music is resumed.
     * Otherwise, the music is not resumed in order to avoid audio collision.
     * </p>
     * Some commands may need to stop their own audio via {@link Command#endAudio()}.
     *
     * @param command the command that determines whether the waiting should happen
     */
    private void waitUntilInput(Command command) {
        if (command.isWaitAble()) {
            System.out.print(Important.changeText("blue", "Napiš cokoli pro pokračování:"));
            Important.loadText();
            command.endAudio();
            if (!player.canPlayCutscene()) {
                player.getCurrentLocation().resumeMusic();
            }
            System.out.println(Important.writeSpace(40));
        }
    }

    private void waitUntilTime(Command command) {
        if (command.isTimeWaitAble()) {
            Important.waitConsole(0.5);
        }
    }

    /**
     * Executes the intro loop at the start of the game.
     * <p>
     * The system repeatedly asks the player for input until the player executes a {@link StartGameCommand}
     * or an {@link ExitCommand}.
     * Commands are checked via {@link #checkCommand(String)} and executed via {@link #execute(List)},
     * just like in {@link #preExecute()}.
     * </p>
     * <p>
     * If a {@link StartGameCommand} or {@link ExitCommand} is executed, {@code exitIntro} is set to true.
     * Afterwards, the system determines whether the game should exit or continue based on {@link #exit}.
     * </p>
     */
    private void executeIntro() {
        boolean exitIntro = false;
        Important.playMusic("intro music");
        while (!exitIntro) {
            System.out.println(player);
            System.out.println("\nNapiš příkaz");
            System.out.print(">> ");
            String command = Important.loadText();
            if (checkCommand(command)) {
                execute(gameLoader.getCommands().get(command).get());
                if (gameLoader.getCommands().get(command).get().get(0) instanceof StartGameCommand || gameLoader.getCommands().get(command).get().get(0) instanceof ExitCommand) {
                    exitIntro = true;
                }
            }
        }
        if (!this.exit) {
            Important.stopMusic("intro music");
            preExecute();
        }
    }

    /**
     * Executes the outro loop at the end of the game.
     * <p>
     * The system repeatedly asks the player for input until the player executes an {@link ExitCommand}.
     * Commands are checked via {@link #checkCommand(String)} and executed via {@link #execute(List)},
     * just like in {@link #preExecute()}.
     * </p>
     * <p>
     * If an {@link ExitCommand} is executed, {@code exitOutro} is set to true.
     * Afterwards, the system exits the game.
     * </p>
     */
    private void executeOutro() {
        player.getCurrentLocation().stopMusic();
        boolean exitOutro = false;
        Important.playMusic("outro music");
        System.out.println(Important.writeSpace(60));
        System.out.println(player);
        while (!exitOutro) {
            System.out.println("\nNapiš příkaz");
            System.out.print(">> ");
            String command = Important.loadText();
            if (checkCommand(command)) {
                execute(gameLoader.getCommands().get(command).get());
                if (gameLoader.getCommands().get(command).get().get(0) instanceof ExitCommand) {
                    exitOutro = true;
                }
            }
        }
    }

}
