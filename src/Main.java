import AroundPlayer.Memory;
import AroundPlayer.Player;
import Commands.Command;
import Commands.MoveCommand;
import Game.Initialization;
import Items.Item;
import Locations.Location;
import Modes.BackpackMode;
import Modes.LocationMode;
import Modes.Mode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

    testMoveCommand();


    }
    public static void testLoader1(){
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Location> locations = new ArrayList<>();
        try {
            InputStream input = new FileInputStream("res\\locations.json");
            InputStream input2 = new FileInputStream("res\\hallwayLocations.json");
            InputStream input3 = new FileInputStream("res\\sideLocations.json");
            Location[] location = mapper.readValue(input, Location[].class);
            Location[] location2 = mapper.readValue(input2, Location[].class);
            Location[] location3 = mapper.readValue(input3, Location[].class);
            locations.addAll(List.of(location));
            locations.addAll(List.of(location2));
            locations.addAll(List.of(location3));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Location location: locations){
            System.out.println(location.toString());
        }
    }

    public static void testLoader2(){
        Initialization init = new Initialization();
        ArrayList<Location> locations = init.getLocations();
        int i = 0;
        for (Location location: locations){
            System.out.println(i+". "+location.getName());
            i++;
        }

        int ii = 0;
        for (Location location: locations) {
            if (location.getFriendlyNPC() != null && location.getFriendlyNPC().getTask().getMemoryPrice().getLocationGift() != null) {
                System.out.println(location.getFriendlyNPC().getTask().getMemoryPrice().getLocationGift().writeAllPossibleLocations());
            }
        }
    }

    public static void testLoader3(){
        Initialization init = new Initialization();
        Player player = init.getPlayer();
        player.addMemory(new Memory("Test", "Test", init.getLocations().get(7), "HALLWAY_002"));
        System.out.println(player.toString());
        player.getCurrentLocation().setEnemyNPC(null);
        System.out.println(player.toString());
        testWait();
        player.switchLocation(player.getCurrentLocation().findLocation("třetí patro"));
        System.out.println(player.scanAndAddPossibleLocations());
        testWait();
        System.out.println(player.toString());
        player.runAway();
        System.out.println(player.toString());
        player.switchLocation(player.getCurrentLocation().findLocation("třetí patro"));
        System.out.println(player.scanAndAddPossibleLocations());



        player.switchMode(new BackpackMode());
        System.out.println(player.toString());
        player.getInventory().addItem(new Item("Test", "Description", "CODE_001", 2));
        player.getInventory().addItem(new Item("Test2", "Description", "CODE_002", 2));
        System.out.println(player.toString());
        player.switchMode(new LocationMode());
        player.getCurrentLocation().addItem(player.getInventory().dropItem("test"));
        System.out.println(player.toString());
        player.switchMode(new BackpackMode());
        System.out.println(player.toString());



    }

    public static void testLocationMovement1(){
        Initialization init = new Initialization();
        Player player = init.getPlayer();
        for (int i = 0; i < 100; i++) {
            player.switchLocation(player.getCurrentLocation().findLocation(player.getCurrentLocation().testNames()));
            System.out.println(player.toString());
            testWait();
        }
    }

    public static void testWait(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void testMoveCommand(){
        Initialization init = new Initialization();
        Player player = init.getPlayer();

        HashMap<String, Supplier<Command>> commands = new HashMap<>();
        HashMap<String, Mode> modes = new HashMap<>();
        modes.put("jdi", new LocationMode());
        commands.put("jdi", ()->{
            String lokace = "Třetí patro";
            return new MoveCommand(player,lokace);
        });

        String key = "jdi";
        if(commands.containsKey(key) && modes.get(key).getInfo().equalsIgnoreCase(player.getMode().getInfo())){
            System.out.println(commands.get(key).get().execute());
        }else {
            System.out.println("Tenhle command není možný");
        }

        System.out.println(player.toString());



    }
}