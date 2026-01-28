package Game;

import AroundPlayer.Player;
import Commands.*;
import Modes.Mode;
import Modes.OutroMode;
import Modes.QuestionMode;

import java.util.*;


public class Console {

    private final Player player;
    private final CommandLoader gameLoader;
    private boolean exit;


    public Console() throws Exception {
        this.exit = false;
        Initialization init = new Initialization();
        this.player = init.getPlayer();
        this.gameLoader = new CommandLoader(player);
        executeIntro();
    }


    public void preExecute() throws Exception {
        System.out.println(Important.writeSpace(50));
        while (!exit) {
            String command;

            if (player.canPlayCutscene()) {
                cutscenePlayer();
                continue;
            }

            if(player.canEnd()){
                exit = true;
                player.setMode(new OutroMode());
                player.getCurrentLocation().stopMusic();
                executeOutro();
                continue;
            }

            if (!player.getMode().getInfo().equalsIgnoreCase(new QuestionMode().getInfo())) {
                player.getCurrentLocation().playMusic();
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
            if (!currentCommand.continuing()) {
                break;
            }
        }
    }

    private boolean checkCommand(String command) throws Exception {
        if (!gameLoader.getCommands().containsKey(command.toLowerCase())) {
            System.out.println(Important.changeText("red", "Akce " + Important.changeText("underline", command) + Important.changeText("red", " neexistuje")));
            return false;
        }
        Mode foundMode = gameLoader.getPossibleCommands().get(command).get();
        if (foundMode != null && !player.getMode().getInfo().equalsIgnoreCase(foundMode.getInfo())) {
            System.out.println(Important.changeText("red", "Akci " + Important.changeText("underline", command) + Important.changeText("red", " nelze nyní provést")));
            return false;
        } else if (foundMode == null) {
            throw new Exception("Commands were loaded badly");
        }
        return true;
    }

    public void cutscenePlayer() {
        Important.pause(player.getCurrentLocation().getName());
        Command command = new CutscenePlayerCommand(player);
        System.out.println(command.execute());
        waitUntilInput(command);
    }


    public void waitUntilInput(Command command) {
        if (command.waitAble()) {
            Important.waitConsole(0.4);
            System.out.print("Napiš cokoli pro pokračování:");
            Important.loadText();
            command.endAudio();
            player.getCurrentLocation().resumeMusic();
        }
    }

    public void waitUntilTime(Command command) {
        if (command.timeWaitAble()) {
            Important.waitConsole(0.5);
        }
    }

    public void executeIntro() throws Exception {
        boolean exitIntro = false;
        Important.playMusic("intro music");
        System.out.println(player);
        while (!exitIntro) {
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
            Important.stopSound("intro music");
            preExecute();
        }
    }

    public void executeOutro() throws Exception {
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
