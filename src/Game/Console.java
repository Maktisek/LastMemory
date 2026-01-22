package Game;

import AroundPlayer.Player;
import Commands.*;
import Modes.LocationMode;
import Modes.Mode;
import Modes.QuestionMode;

import java.util.*;
import java.util.function.Supplier;

public class Console {

    private Player player;
    private HashMap<String, Supplier<ArrayList<Command>>> commands;
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
            return new ArrayList<>(List.of(new MoveCommand(this.player, name), new ScanAndAddCommand(player)));
        });
        commands.put("utéct", () -> new ArrayList<>(List.of(new RunAwayCommand(player))));
        commands.put("pomoc", () -> new ArrayList<>(List.of(new HelpCommand(player))));
        commands.put("opustit", ()-> new ArrayList<>(List.of(new ExitCommand())));
        commands.put("popis lokace", () -> new ArrayList<>(List.of(new ReadLocationDescriptionCommand(player))));
    }

    public void loadPossibleCommands() {
        possibleCommands.put("jdi", LocationMode::new);
        possibleCommands.put("utéct", QuestionMode::new);
        possibleCommands.put("pomoc", () -> player.getMode());
        possibleCommands.put("opusit", () -> player.getMode());
        possibleCommands.put("popis lokace", LocationMode::new);

    }

    public void execute() {
        while (!exit) {
            System.out.println(player.toString());
            System.out.print(">> ");
            String command = sc.nextLine();
            if (commands.containsKey(command)) {
                Mode foundMode = possibleCommands.get(command).get();
                if (foundMode != null && player.getMode().match(foundMode)) {
                    ArrayList<Command> listOfCommands = commands.get(command).get();
                    for (Command currentCommand : listOfCommands){
                        System.out.println(currentCommand.execute());
                        this.exit = currentCommand.exit();
                        waitUntilInput(currentCommand);
                    }
                } else {
                    System.out.println("Akci " + command + " nelze nyní provést");
                }
            } else {
                System.out.println("Akce " + command + " neexistuje");
            }
        }
    }

    public void waitUntilInput(Command command) {
        if (command.waitAble()) {
            System.out.println("Write anything to continue");
            System.out.print(">> ");
            sc.nextLine();
        }
    }
}
