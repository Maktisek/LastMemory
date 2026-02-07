package Game;

import AroundPlayer.Player;
import Commands.*;
import Commands.Game.CutscenePlayerCommand;
import Commands.Game.ExitCommand;
import Commands.Game.StartGameCommand;
import Exceptions.WrongInitializationException;
import Modes.Mode;
import Modes.OutroMode;
import Modes.QuestionMode;

import java.util.*;


public class Console {

    private final Player player;
    private final CommandLoader gameLoader;
    private boolean exit;


    public Console() throws WrongInitializationException {
        this.exit = false;
        Initialization init = new Initialization();
        init.startInitialization();
        this.player = init.getPlayer();
        this.gameLoader = new CommandLoader(player);
    }

    public void start(){
        executeIntro();
    }

    public void preExecute() {
        System.out.println(Important.writeSpace(50));
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

            if(!player.getMode().getInfo().equalsIgnoreCase(new QuestionMode().getInfo())){
                if(player.getCurrentLocation().getSongClip() != null) {
                    player.getCurrentLocation().getSong().resume();
                }else {
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

    public void execute(List<Command> listOfCommands) {
        for (Command currentCommand : listOfCommands) {
            System.out.println(currentCommand.execute());
            this.exit = currentCommand.exit();
            waitUntilInput(currentCommand);
            waitUntilTime(currentCommand);
            if (!currentCommand.isContinuing()) {
                break;
            }
        }
    }

    private boolean checkCommand(String command) {
        if (!gameLoader.getCommands().containsKey(command.toLowerCase())) {
            Important.playSound("wrong sound");
            System.out.println(Important.changeText("red", "Akce " + Important.changeText("underline", command) + Important.changeText("red", " neexistuje")));
            return false;
        }
        Mode foundMode = gameLoader.getPossibleCommands().get(command).get();
        if (foundMode != null && !player.getMode().getInfo().equalsIgnoreCase(foundMode.getInfo())) {
            Important.playSound("wrong sound");
            System.out.println(Important.changeText("red", "Akci " + Important.changeText("underline", command) + Important.changeText("red", " nelze nyní provést")));
            return false;
        }
        return true;
    }

    public void cutscenePlayer() {
        player.getCurrentLocation().pauseMusic();
        Command command = new CutscenePlayerCommand(player);
        System.out.println(command.execute());
        waitUntilInput(command);
        System.out.println(Important.writeSpace(40));
    }


    public void waitUntilInput(Command command) {
        if (command.isWaitAble()) {
            System.out.print("Napiš cokoli pro pokračování:");
            Important.loadText();
            command.endAudio();
            if(!player.canPlayCutscene()) {
                player.getCurrentLocation().resumeMusic();
            }
        }
    }

    public void waitUntilTime(Command command) {
        if (command.isTimeWaitAble()) {
            Important.waitConsole(0.5);
        }
    }

    public void executeIntro() {
        boolean exitIntro = false;
        Important.playMusic("intro music");
        while (!exitIntro) {
            System.out.println(player);
            System.out.println("\nNapiš příkaz");
            System.out.print(">> ");
            String command = Important.loadText();
            if (checkCommand(command)) {
                execute(gameLoader.getCommands().get(command).get());
                if (gameLoader.getCommands().get(command).get().get(0) instanceof StartGameCommand) {
                    exitIntro = ((StartGameCommand) gameLoader.getCommands().get(command).get().get(0)).endIntro();
                }
                if (gameLoader.getCommands().get(command).get().get(0) instanceof ExitCommand) {
                    exitIntro = true;
                }
            }
        }
        if (!exit) {
            Important.stopMusic("intro music");
            preExecute();
        }
    }

    public void executeOutro() {
        player.getCurrentLocation().stopMusic();
        boolean exitOutro = false;
        Important.playMusic("outro music");
        System.out.println(Important.writeSpace(50));
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
