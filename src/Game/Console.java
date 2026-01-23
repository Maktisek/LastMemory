package Game;

import AroundPlayer.Player;
import Commands.*;
import Modes.BackpackMode;
import Modes.LocationMode;
import Modes.Mode;
import Modes.QuestionMode;

import java.util.*;
import java.util.function.Supplier;

public class Console {

    private Player player;
    private HashMap<String, Supplier<List<Command>>> commands;
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
        commands.put("mod", ()->{
            SwitchModeCommand command = new SwitchModeCommand();
            System.out.println(command.writeNamesOfModes());
            System.out.print("Napiš jméno módu: ");
            return new ArrayList<>(List.of(new SwitchModeCommand(sc.nextLine(), player)));
        });
        commands.put("sebrat", () ->{
            if(!player.getCurrentLocation().getItems().isEmpty()) {
                System.out.println("Napiš předmět, který chceš sebrat");
                System.out.print(">>");
                return new ArrayList<>(List.of(new PickItemCommand(player, sc.nextLine())));
            }
            return new ArrayList<>(List.of(new PickItemCommand(player, null)));
        });
        commands.put("položit", () ->{
           if(player.getInventory().getWeight() != 0){
               System.out.println("Napiš předmět, který chceš položit");
               System.out.print(">>");
               return new ArrayList<>(List.of(new DropItemCommand(player, sc.nextLine())));
           }
           return new ArrayList<>(List.of(new DropItemCommand(player, null)));
        });
        commands.put("prohlédnout", () ->{
            if(player.getInventory().getWeight() != 0){
                System.out.println("Napiš předmět, který si chceš prohlédnout");
                System.out.print(">>");
                return new ArrayList<>(List.of(new InspectItemCommand(player, sc.nextLine())));
            }
            return new ArrayList<>(List.of(new InspectItemCommand(player, null)));
        });
        commands.put("odpovědět" , () ->{
            System.out.println("Napiš odpověď");
            System.out.print(">>");
            return List.of(new AnswerEnemyNPCCommand(player, sc.nextLine()));
        });
        commands.put("mluv", () -> List.of(new DialogCommand(player)));
        commands.put("přijmout úkol" , () -> List.of(new AcceptTaskCommand(player)));
        commands.put("zobrazit úkol", () -> List.of(new ShowCurrentTaskCommand(player)));
        commands.put("odevzdat úkol", () -> List.of(new HandInCommand(player), new EvaluateTaskCommand(player)));
        commands.put("vzpomenout" , () ->{
            System.out.println("Napiš jméno vzpomínky");
            System.out.print(">>");
            return List.of(new RecallMemoryCommand(player, sc.nextLine()));
        });
        commands.put("prohlédnout úkol" , () ->{
            System.out.println("Napiš jméno úkolu");
            System.out.print(">>");
            return List.of(new InspectOldTaskCommand(player, sc.nextLine()));
        });
        commands.put("otevřít safe", () ->{
           if(player.getCurrentLocation().availableSafe()){
               System.out.println("Napiš kód");
               System.out.print(">>");
               return List.of(new OpenSafeCommand(player, sc.nextLine()));
           }
           return List.of(new OpenSafeCommand(player, null));
        });
    }



    public void loadPossibleCommands() {
        possibleCommands.put("jdi", LocationMode::new);
        possibleCommands.put("utéct", QuestionMode::new);
        possibleCommands.put("pomoc", () -> player.getMode());
        possibleCommands.put("opustit", () -> player.getMode());
        possibleCommands.put("popis lokace", LocationMode::new);
        possibleCommands.put("mod", () -> player.getMode());
        possibleCommands.put("sebrat", LocationMode::new);
        possibleCommands.put("položit", LocationMode::new);
        possibleCommands.put("prohlédnout", BackpackMode::new);
        possibleCommands.put("odpovědět", QuestionMode::new);
        possibleCommands.put("mluv", LocationMode::new);
        possibleCommands.put("přijmout úkol", LocationMode::new);
        possibleCommands.put("zobrazit úkol", () -> player.getMode());
        possibleCommands.put("odevzdat úkol", LocationMode::new);
        possibleCommands.put("vzpomenout", BackpackMode::new);
        possibleCommands.put("prohlédnout úkol", BackpackMode::new);
        possibleCommands.put("otevřít safe" , LocationMode::new);
    }

    public void execute() {
        while (!exit) {
            System.out.println(player.toString());
            System.out.print(">> ");
            String command = sc.nextLine();
            if (commands.containsKey(command)) {
                Mode foundMode = possibleCommands.get(command).get();
                if (foundMode != null && player.getMode().getInfo().equalsIgnoreCase(foundMode.getInfo())) {
                    List<Command> listOfCommands = commands.get(command).get();
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
            System.out.println("Napiš cokoli pro pokračování");
            System.out.print(">> ");
            sc.nextLine();
        }
    }
}
