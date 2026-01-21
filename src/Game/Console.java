package Game;

import AroundPlayer.Player;
import Commands.*;
import Modes.LocationMode;
import Modes.Mode;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Supplier;

public class Console {

    private Initialization init;
    private Player player;
    private HashMap<String, Supplier<Command>> commands;
    private HashMap<String, Supplier<Mode>> possibleCommands;
    private Scanner sc;
    private boolean exit;


    public Console() {
        this.commands = new HashMap<>();
        this.possibleCommands = new HashMap<>();
        this.sc = new Scanner(System.in);
        this.exit = false;
        Initialization init = new Initialization();
        player = init.getPlayer();
        loadCommands();
        loadPossibleCommands();
        execute();
    }

    public void loadCommands() {
        commands.put("jdi", () -> {
            System.out.println("Další možné lokace: " + player.getCurrentLocation().writeAllPossibleLocations());
            System.out.print("Vlož vstup: ");
            String name = sc.nextLine();
            return new MoveCommand(this.player, name);
        });
        commands.put("utéct", () -> new RunAwayCommand(player));
        commands.put("pomoc", () -> new HelpCommand(player));
        commands.put("opustit", ExitCommand::new);
    }

    public void loadPossibleCommands() {
        possibleCommands.put("jdi", () -> {
            if (player.isFree()) {
                return new LocationMode();
            }
            return null;
        });
        possibleCommands.put("utéct", () -> {
            if (!player.isFree()) {
                return new LocationMode();
            }
            return null;
        });
        possibleCommands.put("pomoc", () -> player.getMode());
        possibleCommands.put("opusit", () -> player.getMode());

    }

    public void execute() {
        while (!exit) {
            System.out.println(player.toString());
            System.out.print(">> ");
            String command = sc.nextLine();
            if (commands.containsKey(command)) {
                Mode foundMode = possibleCommands.get(command).get();
                if (foundMode != null && player.getMode().match(foundMode)) {
                    Command currentCommand = commands.get(command).get();
                    System.out.println(currentCommand.execute());
                    this.exit = currentCommand.exit();
                } else {
                    System.out.println("Akci " + command + " nelze nyní provést");
                }
            }else {
                System.out.println("Akce " + command + " neexistuje");
            }
        }
    }
}
