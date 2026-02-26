package Game;

import AroundPlayer.Player;
import Commands.*;
import Commands.GameCommands.*;
import Commands.ItemsCommands.DropItemCommand;
import Commands.ItemsCommands.InspectItemCommand;
import Commands.ItemsCommands.OpenSafeCommand;
import Commands.ItemsCommands.PickItemCommand;
import Commands.LocationsCommands.ReadLocationDescriptionCommand;
import Commands.MemoriesCommands.RecallMemoryCommand;
import Commands.MovementCommands.MoveCommand;
import Commands.MovementCommands.RunAwayCommand;
import Commands.MovementCommands.ScanAndAddCommand;
import Commands.NPCsCommands.AnswerEnemyNPCCommand;
import Commands.NPCsCommands.DialogCommand;
import Commands.NPCsCommands.ReadFriendlyNPCDescriptionCommand;
import Commands.SavesCommands.DeleteSaveCommand;
import Commands.SavesCommands.LoadGameCommand;
import Commands.SavesCommands.SaveGameCommand;
import Commands.TasksCommands.*;
import Modes.*;

import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

/**
 * Loads and prepares all available commands.
 * <p>
 * Used by {@link Console} to keep the command initialization
 * separate from the main game loop.
 * </p>
 * The {@link #player} reference must point to the original instance created in {@link Console}.
 *
 * @author Matěj Pospíšil
 */
public class CommandLoader {

    private final HashMap<String, Supplier<List<Command>>> commands;
    private final HashMap<String, Supplier<List<Mode>>> possibleCommands;
    private final Player player;
    private final Initialization init;

    public CommandLoader(Player player, Initialization init) {
        this.commands = new HashMap<>();
        this.possibleCommands = new HashMap<>();
        this.player = player;
        this.init = init;
        loadCommands();
        loadPossibleCommands();
    }

    /**
     * Loads and registers all available player commands.
     * <p>
     * Each command keyword is mapped to a {@link Supplier} that creates
     * one or more {@link Command} instances when invoked.
     * Some commands may require additional user input before the
     * command objects are created.
     * </p>
     */
    public void loadCommands() {
        commands.put("jít", () -> {
            System.out.println("Další možné lokace: " + player.getCurrentLocation().writeAllPossibleLocations());
            System.out.print("Vlož vstup: ");
            return List.of(new MoveCommand(this.player, Important.loadText()), new ScanAndAddCommand(player));
        });
        commands.put("utéct", () -> List.of(new RunAwayCommand(player)));
        commands.put("pomoc", () -> List.of(new HelpCommand(player)));
        commands.put("opustit", () -> List.of(new ExitCommand()));
        commands.put("popis lokace", () -> List.of(new ReadLocationDescriptionCommand(player)));
        commands.put("mod", () -> {
            SwitchModeCommand command = new SwitchModeCommand(player);
            System.out.println(command.writeNamesOfModes());
            System.out.print("Napiš jméno módu: ");
            command.setMode(Important.loadText());
            return List.of(command);
        });
        commands.put("sebrat", () -> {
            if (!player.getCurrentLocation().getItems().isEmpty()) {
                System.out.println("Napiš předmět, který chceš sebrat");
                System.out.print(">>");
                return List.of(new PickItemCommand(player, Important.loadText()));
            }
            return List.of(new PickItemCommand(player, null));
        });
        commands.put("položit", () -> {
            if (player.getInventory().getWeight() != 0) {
                System.out.println("Napiš jméno předmětu, který chceš položit");
                System.out.print(">>");
                return List.of(new DropItemCommand(player, Important.loadText()));
            }
            return List.of(new DropItemCommand(player, null));
        });
        commands.put("prohlédnout", () -> {
            if (player.getInventory().getWeight() != 0) {
                System.out.println("Napiš předmět, který si chceš prohlédnout");
                System.out.print(">>");
                return List.of(new InspectItemCommand(player, Important.loadText()));
            }
            return List.of(new InspectItemCommand(player, null));
        });
        commands.put("odpovědět", () -> {
            System.out.println("Napiš odpověď");
            System.out.print(">>");
            return List.of(new AnswerEnemyNPCCommand(player, Important.loadText()));
        });
        commands.put("mluvit", () -> List.of(new DialogCommand(player)));
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
            if (player.getCurrentLocation().isSafeAvailable()) {
                System.out.println("Napiš kód");
                System.out.print(">>");
                return List.of(new OpenSafeCommand(player, Important.loadText().toUpperCase()));
            }
            return List.of(new OpenSafeCommand(player, null));
        });
        commands.put("info postava", () -> List.of(new ReadFriendlyNPCDescriptionCommand(player)));
        commands.put("spustit hru", () -> List.of(new StartGameCommand(player)));
        commands.put("informace", () -> List.of(new WriteTxtFileCommand("/TextFiles/aboutGame.txt")));
        commands.put("jak hrát", () -> List.of(new WriteTxtFileCommand("/TextFiles/howToPlay.txt")));
        commands.put("načíst hru", () -> {
            if(!Important.hasSave()){
                return List.of(new LoadGameCommand(this.player, null));
            }
            if(player.getMode().getInfo() != ModeType.options){
                System.out.println("Dostupné savy: " + Important.writeNamesOfSavedSaves());
            }
            System.out.print("Napiš jaký save si přeješ využít:");
            return List.of(new LoadGameCommand(this.player, Important.loadText()));
        });
        commands.put("uložit", () -> {
            System.out.println("(Pro vytvoření nového savu stačí využít neexistující jméno)");
            System.out.print("Napiš jméno savu:");
            return List.of(new SaveGameCommand(init, Important.loadText(), player));
        });
        commands.put("vymazat save", () -> {
            if(!Important.hasSave()){
                return List.of(new DeleteSaveCommand(null, player));
            }
            System.out.print("Napiš jméno savu, který si přeješ vymazat:");
            return List.of(new DeleteSaveCommand(Important.loadText(), player));
        });

    }

    /**
     * Represents loading system of instances of {@link Mode}, which are put into {@link #possibleCommands} with their own
     * command keyword.
     * <p>
     * This specific system is designed in order to block some of the commands for the player in specific game
     * situation.
     * </p>
     * For example:
     * <p>
     * If the player’s current mode is set to {@link BackpackMode} and he wants to call "jdi" command, then
     * the game will check if the player’s mode matches with the "jdi" command’s mode.
     * In this instance will not, so the player will not be able to proceed "jdi" command.
     * </p>
     * <p>
     * The keyword must also exist in {@link #commands}, otherwise
     * the validation system will not work correctly.
     * </p>
     */
    public void loadPossibleCommands() {
        possibleCommands.put("jít", () -> List.of(new LocationMode()));
        possibleCommands.put("utéct", () -> List.of(new QuestionMode()));
        possibleCommands.put("pomoc", () -> List.of(player.getMode()));
        possibleCommands.put("opustit", () -> List.of(player.getMode()));
        possibleCommands.put("popis lokace", () -> List.of(new LocationMode()));
        possibleCommands.put("mod", () -> List.of(new LocationMode(), new OptionsMode(), new BackpackMode()));
        possibleCommands.put("sebrat", () -> List.of(new LocationMode()));
        possibleCommands.put("položit", () -> List.of(new BackpackMode()));
        possibleCommands.put("prohlédnout", () -> List.of(new BackpackMode()));
        possibleCommands.put("odpovědět", () -> List.of(new QuestionMode()));
        possibleCommands.put("mluvit", () -> List.of(new LocationMode()));
        possibleCommands.put("přijmout úkol", () -> List.of(new LocationMode()));
        possibleCommands.put("zobrazit úkol", () -> List.of(new LocationMode(), new BackpackMode()));
        possibleCommands.put("odevzdat úkol", () -> List.of(new LocationMode()));
        possibleCommands.put("vzpomenout", () -> List.of(new BackpackMode()));
        possibleCommands.put("prohlédnout úkol", () -> List.of(new BackpackMode()));
        possibleCommands.put("otevřít safe", () -> List.of(new LocationMode()));
        possibleCommands.put("info postava", () -> List.of(new LocationMode()));
        possibleCommands.put("spustit hru", () -> List.of(new IntroMode()));
        possibleCommands.put("informace", () -> List.of(new OutroMode()));
        possibleCommands.put("jak hrát", () -> List.of(player.getMode()));
        possibleCommands.put("načíst hru", () -> List.of(new IntroMode(), new OptionsMode()));
        possibleCommands.put("uložit", () -> List.of(new OptionsMode()));
        possibleCommands.put("vymazat save", () -> List.of(new OptionsMode()));
    }

    public HashMap<String, Supplier<List<Command>>> getCommands() {
        return commands;
    }

    public HashMap<String, Supplier<List<Mode>>> getPossibleCommands() {
        return possibleCommands;
    }
}
