package Game;


import AroundPlayer.Player;
import AudioSystem.Audio;
import Exceptions.WrongInitializationException;
import Locations.Location;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Initialization {


    ObjectMapper mapper;
    ArrayList<Location> locations;
    ArrayList<Location> tempLocations;
    Player player;

    public Initialization() throws WrongInitializationException {
        this.locations = new ArrayList<>();
        this.tempLocations = new ArrayList<>();
        this.mapper = new ObjectMapper();
        loadSideLocations();
    }

    /**
     * Loads all side locations from res\Jsons\sideLocations.json file and adds them into locations list
     */
    public void loadSideLocations() throws WrongInitializationException {
        try (InputStream input = new FileInputStream("res\\Jsons\\sideLocations.json");) {
            Location[] sideLocations = mapper.readValue(input, Location[].class);
            locations.addAll(List.of(sideLocations));
        } catch (IOException e) {
            throw new WrongInitializationException(e.getMessage());
        }
        loadHallwayLocationsLocations();
    }

    /**
     * Loads all hallway locations from res\Jsons\hallwayLocations.json file and adds them into locations list.
     */
    public void loadHallwayLocationsLocations() throws WrongInitializationException {
        try (InputStream input = new FileInputStream("res\\Jsons\\hallwayLocations.json");) {
            Location[] hallwayLocations = mapper.readValue(input, Location[].class);
            locations.addAll(List.of(hallwayLocations));
        } catch (IOException e) {
            throw new WrongInitializationException("Wrong hallway locations load");
        }
        loadMainLocations();
    }

    /**
     * Loads all main locations from res\Jsons\locations.json file and adds them into temporary locations list.
     */
    public void loadMainLocations() throws WrongInitializationException {
        try (InputStream input = new FileInputStream("res\\Jsons\\locations.json");) {
            Location[] mainLocations = mapper.readValue(input, Location[].class);
            tempLocations.addAll(List.of(mainLocations));
        } catch (IOException e) {
            throw new WrongInitializationException("Wrong main locations load");
        }
        connectMainLocations();
    }

    /**
     * Connects all main location together throughout their friendlyNPC's tasks memory prices.
     * The cycle goes from the last location to the first location always connecting the next location with the current location.
     * Then it adds all main locations into locations list.
     */
    public void connectMainLocations() throws WrongInitializationException {
        for (int i = tempLocations.size() - 1; i > 0; i--) {
            tempLocations.get(i - 1).getFriendlyNPC().getTask().getMemoryPrice().setLocationGift(tempLocations.get(i));
        }
        locations.addAll(tempLocations);
        setReadyPossibleLocationArrays();
    }

    /**
     * Initializes all locations possible locations array list.
     */
    public void setReadyPossibleLocationArrays() throws WrongInitializationException {
        for (Location location : locations) {
            location.setPossibleLocations(new ArrayList<>());
        }
        loadBasicLocationConnection();
    }

    /**
     * Connects location via special .csv file: res\Jsons\basicLocationConnections.csv
     */
    public void loadBasicLocationConnection() throws WrongInitializationException {
//        int a = 0;
//        for (Location location: locations){
//            System.out.println(a+". "+location);
//            a++;
//        }
        try (BufferedReader br = new BufferedReader(new FileReader("res\\CsvFiles\\basicLocationConnections.csv"))) {
            br.readLine();
            String line;
            int index = 0;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(">");
                for (int i = 0; i < data.length; i++) {
                    locations.get(index).addPossibleLocation(locations.get(Integer.parseInt(data[i])));
                }
                index++;
            }
        } catch (IOException e) {
            throw new WrongInitializationException("There is a problem with BufferedReader");
        }
//        int b = 0;
//        for (Location location: locations){
//            System.out.println(b+". "+location);
//            b++;
//        }
        setAllMusic();
    }


    public void setAllMusic() throws WrongInitializationException {
        Audio[] audios = loadAllSongs();
        for (Audio audio : audios) {
            Location location = findLocation(audio.getTitle());
            if (location != null) {
                Objects.requireNonNull(findLocation(location.getName())).setSong(audio);
            } else {
                throw new WrongInitializationException("Wrong song name input");
            }
        }
        loadPlayer();
    }

    /**
     * It initializes player and sets his current location to the start location, which is location on the index 11 in locations list.
     */

    public void loadPlayer() {
        //11 je startovní lokace
        this.player = new Player(locations.get(17));
    }

    public Audio[] loadAllSongs() throws WrongInitializationException {
        try (InputStream input = new FileInputStream("res\\Jsons\\locationMusic.json")) {
            return mapper.readValue(input, Audio[].class);
        } catch (IOException e) {
            throw new WrongInitializationException("Audios were not loaded properly");
        }
    }

    private Location findLocation(String name) {
        for (Location location : locations) {
            if (location.getName().equalsIgnoreCase(name)) {
                return location;
            }
        }
        return null;
    }


    public Player getPlayer() {
        return player;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }
}
