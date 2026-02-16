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

/**
 * This class is responsible for loading the entire game.
 * <p>
 * It uses the Jackson library to load data from JSON files.
 * </p>
 * <p>
 * At the end, a player is set up for the {@link Console}.
 * </p>
 *
 * @author Matěj Pospíšil
 */
public class Initialization {


    private final ObjectMapper mapper;
    private final ArrayList<Location> locations;
    private final ArrayList<Location> tempLocations;
    private Player player;

    public Initialization() {
        this.locations = new ArrayList<>();
        this.tempLocations = new ArrayList<>();
        this.mapper = new ObjectMapper();
    }

    /**
     * This method creates a chain of methods for loading the game.
     * <p>
     * The order cannot be changed, because this may cause some unexpected problems.
     * </p>
     *
     * @throws WrongInitializationException when there is problem with the initialization
     */
    public void startInitialization() throws WrongInitializationException {
        loadSideLocations();
        loadHallwayLocationsLocations();
        loadMainLocations();
        connectMainLocations();
        setReadyPossibleLocationArrays();
        loadLocationsConnection();
        setAllMusic();
        loadPlayer();
    }

    /**
     * Loads all side locations from the file {@code /Jsons/sideLocations.json} and adds them into {@link #locations}.
     *
     * @throws WrongInitializationException if the file cannot be found during the loading process or the Json file is
     *                                      not loaded correctly.
     */
    public void loadSideLocations() throws WrongInitializationException {
        InputStream input = Initialization.class.getResourceAsStream("/Jsons/sideLocations.json");
        checkInput(input, "/Jsons/sideLocations.json");
        try (input) {
            Location[] sideLocations = mapper.readValue(input, Location[].class);
            locations.addAll(List.of(sideLocations));
        } catch (Exception e) {
            throw new WrongInitializationException(Important.changeText("red", "There is a problem with the Json file"));
        }
    }

    /**
     * Loads all hallway from the file {@code /Jsons/hallwayLocations.json} and adds them into {@link #locations}.
     *
     * @throws WrongInitializationException if the file cannot be found during the loading process or the Json file is
     *                                      not loaded correctly.
     */
    public void loadHallwayLocationsLocations() throws WrongInitializationException {
        InputStream input = Initialization.class.getResourceAsStream("/Jsons/hallwayLocations.json");
        checkInput(input, "/Jsons/hallwayLocations.json");
        try (input) {
            Location[] hallwayLocations = mapper.readValue(input, Location[].class);
            locations.addAll(List.of(hallwayLocations));
        } catch (Exception e) {
            throw new WrongInitializationException(Important.changeText("red", "There is a problem with the Json file"));
        }
    }

    /**
     * Loads all main locations from the file {@code /Jsons/locations.json} and adds them into {@link #tempLocations}.
     *
     * @throws WrongInitializationException if an I/O error occurs during the loading process
     */
    public void loadMainLocations() throws WrongInitializationException {
        InputStream input = Initialization.class.getResourceAsStream("/Jsons/locations.json");
        checkInput(input, "/Jsons/locations.json");
        try (input) {
            Location[] mainLocations = mapper.readValue(input, Location[].class);
            tempLocations.addAll(List.of(mainLocations));
        } catch (Exception e) {
            throw new WrongInitializationException(Important.changeText("red", "There is a problem with the Json file"));
        }
    }

    /**
     * Connects all main locations by linking each location's friendly NPC task's memory price
     * to the next location in the sequence.
     * <p>
     * The linking starts from the last location and goes to the first, always connecting
     * the current location to the next.
     * </p>
     * <p>
     * After linking, all locations from {@link #tempLocations} are added to {@link #locations}.
     * </p>
     */
    public void connectMainLocations() {
        for (int i = tempLocations.size() - 1; i > 0; i--) {
            tempLocations.get(i - 1).getFriendlyNPC().getTask().getMemoryPrice().setLocationGift(tempLocations.get(i));
        }
        locations.addAll(tempLocations);
    }

    /**
     * Initializes the possible locations list for all locations,
     * so that it can be filled later.
     */
    public void setReadyPossibleLocationArrays() {
        for (Location location : locations) {
            location.setPossibleLocations(new ArrayList<>());
        }
    }

    /**
     * Connects all locations according to a CSV file.
     * <p>
     * The connections are read from the CSV file {@code /Jsons/basicLocationConnections.csv}.
     * </p>
     * <p>
     * Each location has its own index in {@link #locations}, and each line in the CSV file
     * represents an individual location. The line contains the indexes of neighboring locations,
     * and each location is connected to them accordingly.
     * </p>
     * <p>
     * The indexes in the CSV file should be written in the format: X>Y>Z
     * </p>
     *
     * @throws WrongInitializationException if the file cannot be found during the loading process or the CSV file is
     *                                      not loaded correctly.
     */
    public void loadLocationsConnection() throws WrongInitializationException {
        InputStream input = Initialization.class.getResourceAsStream("/CsvFiles/basicLocationConnections.csv");
        checkInput(input, "/CsvFiles/basicLocationConnections.csv");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
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
        } catch (Exception e) {
            throw new WrongInitializationException(Important.changeText("red", "There is a problem with the CSV file"));
        }
    }

    private void connectAllLocations(){



    }

    /**
     * Loads all {@link Audio} instances from the JSON file {@code /Jsons/locationMusic.json}.
     *
     * @return an array of {@link Audio} instances loaded from the file
     * @throws WrongInitializationException if the file cannot be found during the loading process or the CSV file is
     *                                      not loaded correctly.
     */
    public Audio[] loadAllSongs() throws WrongInitializationException {
        InputStream input = Initialization.class.getResourceAsStream("/Jsons/locationMusic.json");
        checkInput(input, "/Jsons/locationMusic.json");
        try (input) {
            return mapper.readValue(input, Audio[].class);
        } catch (Exception e) {
            throw new WrongInitializationException(Important.changeText("red", "There is a problem with the Json file"));
        }
    }

    /**
     * Assigns an {@link Audio} instance to each corresponding location.
     * <p>
     * Uses {@link #loadAllSongs()} for audio initialization.
     * </p>
     * <p>
     * Locations without a matching audio will remain without music.
     * </p>
     *
     * @throws WrongInitializationException if an audio title does not match any location
     */
    public void setAllMusic() throws WrongInitializationException {
        Audio[] audios = loadAllSongs();
        for (Audio audio : audios) {
            Location location = findLocation(audio.getTitle());
            if (location != null) {
                Objects.requireNonNull(findLocation(location.getName())).setSong(audio);
            } else {
                throw new WrongInitializationException(Important.changeText("red", "Wrong song name input"));
            }
        }
    }

    /**
     * Initializes {@link #player} and sets the player's current location
     * to the location at index 11 in {@link #locations}.
     *
     * @throws WrongInitializationException if there is a problem initializing the player
     */
    public void loadPlayer() throws WrongInitializationException {
        this.player = new Player(locations.get(11));
    }


    private void checkInput(InputStream input, String path) throws WrongInitializationException {
        if (input == null) {
            throw new WrongInitializationException(Important.changeText("red", "The file"+ path +" is missing from the resources folder."));
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
