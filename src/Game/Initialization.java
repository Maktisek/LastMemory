package Game;


import AroundPlayer.Player;
import Locations.Location;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Initialization {


    ObjectMapper mapper;
    ArrayList<Location> locations;
    ArrayList<Location> tempLocations;
    Player player;

    public Initialization() {
        this.locations = new ArrayList<>();
        this.tempLocations = new ArrayList<>();
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
            tempLocations.addAll(List.of(mainLocations));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        connectMainLocations();
    }

    public void connectMainLocations(){
        for (int i = tempLocations.size() - 1; i > 0; i--) {
            tempLocations.get(i-1).getFriendlyNPC().getTask().getMemoryPrice().setLocationGift(tempLocations.get(i));
        }
        locations.addAll(tempLocations);
        setReadyPossibleLocationArrays();
    }

    public void setReadyPossibleLocationArrays(){
        for (Location location: locations){
            location.setPossibleLocations(new ArrayList<>());
        }
        loadBasicLocationConnection();
    }

    public void loadBasicLocationConnection(){
//        int a = 0;
//        for (Location location: locations){
//            System.out.println(a+". "+location);
//            a++;
//        }
        try (BufferedReader br = new BufferedReader(new FileReader("res\\basicLocationConnections.csv"))){
            br.readLine();
            String line;
            int index = 0;
            while ((line = br.readLine()) != null){
                String[] data = line.split(">");
                for (int i = 0; i < data.length; i++) {
                    locations.get(index).addPossibleLocation(locations.get(Integer.parseInt(data[i])));
                }
                index++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loadPlayer();
    }

    public void loadPlayer(){
        this.player = new Player(locations.get(11));
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }
}
