import AroundPlayer.Player;
import Game.Initialization;
import Locations.Location;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        testLoader3();


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
        System.out.println(player.getCurrentLocation());


    }
}