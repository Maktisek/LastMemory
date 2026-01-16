package Game;


import AroundPlayer.Player;
import Locations.Location;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Initialization {


    ObjectMapper mapper;
    ArrayList<Location> locations;
    Player player;

    public Initialization() {
        this.locations = new ArrayList<>();
        this.mapper = new ObjectMapper();
        loadSideLocations();
    }

    public void loadSideLocations(){
        try (InputStream input = new FileInputStream("res\\sideLocations.json");){
            Location[] sideLocations = mapper.readValue(input, Location[].class);
            locations.addAll(List.of(sideLocations));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loadHallwayLocationsLocations();
    }

    public void loadHallwayLocationsLocations(){
        try (InputStream input = new FileInputStream("res\\hallwayLocations.json");){
            Location[] hallwayLocations = mapper.readValue(input, Location[].class);
            locations.addAll(List.of(hallwayLocations));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loadMainLocations();
    }

    public void loadMainLocations(){
        try (InputStream input = new FileInputStream("res\\locations.json");){
            Location[] mainLocations = mapper.readValue(input, Location[].class);
            locations.addAll(List.of(mainLocations));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void connectMainLocations(){
        //TODO connectMainLocations metoda chybi
    }

    public void loadPlayerAndConnectOthers(){
        //TODO loadPlayerAndConnectOthers metoda chybi
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }
}
