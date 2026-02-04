package Game;

import AroundPlayer.Player;
import Commands.*;
import Commands.Game.*;
import Commands.Items.DropItemCommand;
import Commands.Items.InspectItemCommand;
import Commands.Items.OpenSafeCommand;
import Commands.Items.PickItemCommand;
import Commands.Locations.ReadLocationDescriptionCommand;
import Commands.Memories.RecallMemoryCommand;
import Commands.Movement.MoveCommand;
import Commands.Movement.RunAwayCommand;
import Commands.Movement.ScanAndAddCommand;
import Commands.NPCs.AnswerEnemyNPCCommand;
import Commands.NPCs.DialogCommand;
import Commands.NPCs.ReadFriendlyNPCDescriptionCommand;
import Commands.Tasks.*;
import Modes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

public class CommandLoader {

    private final HashMap<String, Supplier<List<Command>>> commands;
    private final HashMap<String, Supplier<Mode>> possibleCommands;
    private final Player player;

    public CommandLoader(Player player) {
        this.commands = new HashMap<>();
        this.possibleCommands = new HashMap<>();
        this.player = player;
        loadCommands();
        loadPossibleCommands();
    }

    public void loadCommands() {
        commands.put("jdi", () -> {
            System.out.println("Další možné lokace: " + player.getCurrentLocation().writeAllPossibleLocations());
            System.out.print("Vlož vstup: ");
            return new ArrayList<>(List.of(new MoveCommand(this.player, Important.loadText()), new ScanAndAddCommand(player)));
        });
        commands.put("utéct", () -> new ArrayList<>(List.of(new RunAwayCommand(player))));
        commands.put("pomoc", () -> new ArrayList<>(List.of(new HelpCommand(player))));
        commands.put("opustit", () -> new ArrayList<>(List.of(new ExitCommand())));
        commands.put("popis lokace", () -> new ArrayList<>(List.of(new ReadLocationDescriptionCommand(player))));
        commands.put("mod", () -> {
            SwitchModeCommand command = new SwitchModeCommand();
            System.out.println(command.writeNamesOfModes());
            System.out.print("Napiš jméno módu: ");
            return new ArrayList<>(List.of(new SwitchModeCommand(Important.loadText(), player)));
        });
        commands.put("sebrat", () -> {
            if (!player.getCurrentLocation().getItems().isEmpty()) {
                System.out.println("Napiš předmět, který chceš sebrat");
                System.out.print(">>");
                return new ArrayList<>(List.of(new PickItemCommand(player, Important.loadText())));
            }
            return new ArrayList<>(List.of(new PickItemCommand(player, null)));
        });
        commands.put("položit", () -> {
            if (player.getInventory().getWeight() != 0) {
                System.out.println("Napiš předmět, který chceš položit");
                System.out.println("Tvé předměty: " + player.getInventory().writeItems());
                System.out.print(">>");
                return new ArrayList<>(List.of(new DropItemCommand(player, Important.loadText())));
            }
            return new ArrayList<>(List.of(new DropItemCommand(player, null)));
        });
        commands.put("prohlédnout", () -> {
            if (player.getInventory().getWeight() != 0) {
                System.out.println("Napiš předmět, který si chceš prohlédnout");
                System.out.print(">>");
                return new ArrayList<>(List.of(new InspectItemCommand(player, Important.loadText())));
            }
            return new ArrayList<>(List.of(new InspectItemCommand(player, null)));
        });
        commands.put("odpovědět", () -> {
            System.out.println("Napiš odpověď");
            System.out.print(">>");
            return List.of(new AnswerEnemyNPCCommand(player, Important.loadText()));
        });
        commands.put("mluv", () -> List.of(new DialogCommand(player)));
        commands.put("přijmout úkol", () -> List.of(new AcceptTaskCommand(player)));
        commands.put("zobrazit úkol", () -> List.of(new ShowCurrentTaskCommand(player)));
        commands.put("odevzdat úkol", () -> List.of(new HandInCommand(player), new EvaluateTaskCommand(player)));
        commands.put("vzpomenout", () -> {
            System.out.println("Napiš jméno vzpomínky");
            System.out.print(">>");
            return List.of(new RecallMemoryCommand(player, Important.loadText()));
        });
        commands.put("prohlédnout úkol", () -> {
            System.out.println("Napiš jméno úkolu");
            System.out.print(">>");
            return List.of(new InspectOldTaskCommand(player, Important.loadText()));
        });
        commands.put("otevřít safe", () -> {
            if (player.getCurrentLocation().availableSafe()) {
                System.out.println("Napiš kód");
                System.out.print(">>");
                return List.of(new OpenSafeCommand(player, Important.loadText()));
            }
            return List.of(new OpenSafeCommand(player, null));
        });
        commands.put("info postava", () -> List.of(new ReadFriendlyNPCDescriptionCommand(player)));
        commands.put("spustit hru", () -> List.of(new StartGameCommand(player)));
        commands.put("informace", () -> List.of(new GameEndInformationCommand()));
        commands.put("jak hrát", () -> List.of(new GameInformationCommand()));
    }


    public void loadPossibleCommands() {
        possibleCommands.put("jdi", LocationMode::new);
        possibleCommands.put("utéct", QuestionMode::new);
        possibleCommands.put("pomoc", player::getMode);
        possibleCommands.put("opustit", player::getMode);
        possibleCommands.put("popis lokace", LocationMode::new);
        possibleCommands.put("mod", player::specialGetMode);
        possibleCommands.put("sebrat", LocationMode::new);
        possibleCommands.put("položit", LocationMode::new);
        possibleCommands.put("prohlédnout", BackpackMode::new);
        possibleCommands.put("odpovědět", QuestionMode::new);
        possibleCommands.put("mluv", LocationMode::new);
        possibleCommands.put("přijmout úkol", LocationMode::new);
        possibleCommands.put("zobrazit úkol", player::specialGetMode);
        possibleCommands.put("odevzdat úkol", LocationMode::new);
        possibleCommands.put("vzpomenout", BackpackMode::new);
        possibleCommands.put("prohlédnout úkol", BackpackMode::new);
        possibleCommands.put("otevřít safe", LocationMode::new);
        possibleCommands.put("info postava", LocationMode::new);
        possibleCommands.put("spustit hru", IntroMode::new);
        possibleCommands.put("informace", OutroMode::new);
        possibleCommands.put("jak hrát", player::getMode);
    }

    public HashMap<String, Supplier<List<Command>>> getCommands() {
        return commands;
    }

    public HashMap<String, Supplier<Mode>> getPossibleCommands() {
        return possibleCommands;
    }
}
