package Game;

import AroundPlayer.Player;
import Commands.*;
import Modes.Mode;
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
        preExecute();
    }


    public void preExecute() throws Exception {
        while (!exit) {
            String command;
            if (player.canPlayCutscene()) {
                cutscenePlayer();
                continue;
            }
            if (!player.getMode().getInfo().equalsIgnoreCase(new QuestionMode().getInfo())) {
                Important.playLocationSong(player.getCurrentLocation());
            }
            System.out.println(player);
            System.out.print(">> ");
            command = Important.loadText();

            if (!gameLoader.getCommands().containsKey(command.toLowerCase())) {
                System.out.println(Important.changeText("red", "Akce " + Important.changeText("underline", command) + Important.changeText("red", " neexistuje")));
                continue;
            }
            Mode foundMode = gameLoader.getPossibleCommands().get(command).get();
            if (foundMode != null && !player.getMode().getInfo().equalsIgnoreCase(foundMode.getInfo())) {
                System.out.println(Important.changeText("red", "Akci " + Important.changeText("underline", command) + Important.changeText("red", " nelze nyní provést")));
                continue;
            } else if (foundMode == null) {
                throw new Exception("Commands were loaded badly");
            }
            execute(gameLoader.getCommands().get(command).get());
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

    public void cutscenePlayer() {
        Important.pause(player.getCurrentLocation().getName());
        Command command = new CutscenePlayerCommand(player);
        System.out.println(command.execute());
        waitUntilInput(command);
    }


    public void waitUntilInput(Command command) {
        if (command.waitAble()) {
            Important.waitConsole(0.4);
            System.out.println("Napiš cokoli pro pokračování");
            System.out.print(">> ");
            Important.loadText();
            command.endAudio();
            Important.resume(player.getCurrentLocation().getName());
        }
    }

    public void waitUntilTime(Command command) {
        if (command.timeWaitAble()) {
            Important.waitConsole(0.5);
        }
    }
}
