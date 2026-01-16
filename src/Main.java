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

        Initialization init = new Initialization();
        ArrayList<Location> locations = init.getLocations();
        for (Location location: locations){
            System.out.println(location.toString());
        }
        Player player = init.getPlayer();

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
}