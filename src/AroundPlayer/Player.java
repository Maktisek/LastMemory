package AroundPlayer;

import Cutscenes.CutsceneLoader;
import Exceptions.WrongInitializationException;
import Game.Important;
import Items.Task;
import Locations.Location;
import Modes.IntroMode;
import Modes.LocationMode;
import Modes.Mode;
import Modes.QuestionMode;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents a player, who is the most essential part of the whole game.
 * All the important information are being stored here. <p> Such as: <p>
 * {@link #currentLocation} represents a location, in which the player currently is. <p>
 * {@link #previousLocation} represents a location, in which the player was one movement ago. <p>
 * {@link #mode} stands for currently selected mode.
 * @author Matěj Pospíšil
 */
public class Player {

    private Inventory inventory;
    private ArrayList<Memory> collectedMemories;
    private ArrayList<Task> doneTasks;
    private Task currentTask;
    private Location currentLocation;
    private Location previousLocation;
    private Mode mode;
    private CutsceneLoader cutscenes;

    /**
     * Special constructor, which prepares the player for the start of the game.
     * @param startLocation the location, in which the player will start.
     */
    public Player(Location startLocation) throws WrongInitializationException {
        this.inventory = new Inventory();
        this.collectedMemories = new ArrayList<>();
        this.doneTasks = new ArrayList<>();
        this.currentTask = null;
        this.currentLocation = startLocation;
        this.previousLocation = null;
        this.mode = new IntroMode();
        this.cutscenes = CutsceneLoader.loadCutscenes();
    }

    public Player() {
    }

    public boolean addMemory(Memory memory) {
        return collectedMemories.add(memory);
    }

    private void sortMemories() {
        Collections.sort(collectedMemories);
    }

    public boolean hasCollectedMemory(String name) {
        for (Memory memory : collectedMemories) {
            if (memory.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Displays a memory content with an ASCII art on top. The ASCII art has to be loaded from
     * {@code res\\TextFiles\\asciiMemory.txt}.
     * <p>
     *     If the memory is found, then the memory {@link Memory#switchOpened()} is done.
     * </p>
     *
     * @param name the name of the memory to be shown
     * @return the memory’s content; when not found, then a message indicating that
     */
    public String writeMemory(String name) {
        for (Memory memory : collectedMemories) {
            if (memory.getName().equalsIgnoreCase(name)) {
                memory.switchOpened();
                String ascii = Important.readTxtFiles("res\\TextFiles\\asciiMemory.txt", 0);
                String headText = Important.asciiHeadTextHelper(memory.getDescription(), ascii);
                return Important.writeSpace(40) + Important.changeText("bold", Important.changeText("pink", headText) + "\n" + memory.getDescription());
            }
        }
        return Important.changeText("red", "Vzpomínka " + Important.changeText("underline", name) + " neexistuje");
    }

    public boolean hasOldTask(String name) {
        for (Task task : doneTasks) {
            if (task.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }


    public String writeOldTask(String name) {
        for (Task task : doneTasks) {
            if (task.getName().equalsIgnoreCase(name)) {
                return task.getName() + "\n" + task.getDescription();
            }
        }
        return "";
    }

    public boolean addCurrentTask(Task task) {
        if (currentTask == null) {
            this.currentTask = task;
            return true;
        }
        return false;
    }

    public void deleteTask() {
        this.doneTasks.add(currentTask);
        currentTask = null;
    }

    /**
     * Represents the change of current location and stands for the most important part of the whole movement system.
     * {@link #currentLocation} is moved to {@link #previousLocation} before changing.
     * If the new current location has an enemyNPC in, then the mode is switched into question mode.
     * This is checked using the {@link #currentLocation}'s isFree() method
     *
     * @param location The location to be changed into.
     * @return true if the operation went successful, false if not (happens only if the param location is null)
     */
    public boolean switchLocation(Location location) {
        if (location != null) {
            previousLocation = currentLocation;
            currentLocation = location;
            if (!currentLocation.isFree()) {
                switchMode(new QuestionMode());
                return true;
            }
            return true;
        }
        return false;
    }


    /**
     * Represents a switch between {@link #currentLocation} and {@link #previousLocation}. <p>
     * It has to change {@link #mode} to {@link LocationMode}, when the {@link #previousLocation} is not null.
     * @return true if the {@link #previousLocation} was not null, false if {@link #previousLocation} was null.
     */
    public boolean runAway() {
        if (previousLocation != null) {
            currentLocation = previousLocation;
            previousLocation = null;
            switchMode(new LocationMode());
            return true;
        }
        return false;
    }

    /**
     * Represents a scan of {@link #collectedMemories} in order to find new locations, which can be then added into current location.
     * It goes sequentially through {@link #collectedMemories} and if the giftLocation is not null and the code of the memory matches with the code of {@link #currentLocation}, then
     * new location is added into {@link #currentLocation}'s possible locations list.
     *
     * @return Names of added location names.
     */
    public String scanAndAddPossibleLocations() {
        ArrayList<String> names = new ArrayList<>();
        for (Memory memory : collectedMemories) {
            if (memory.getLocationGift() != null && memory.getCode().equalsIgnoreCase(currentLocation.getCode())) {
                Location temp = memory.giveLocation();
                currentLocation.addPossibleLocation(temp);
                names.add(Important.changeText("underline", temp.getName()));
            }
            if (!names.isEmpty()) {
                return "Nové odemklé lokace: " + String.join(", ", names);
            }
        }
        return null;
    }

    public boolean canEnd() {
        return collectedMemories.size() == 10;
    }

    public void switchMode(Mode mode) {
        if (mode != null) {
            this.mode = mode;
        }
    }

    public String writeMemories() {
        ArrayList<String> names = new ArrayList<>();
        sortMemories();
        for (Memory memory : collectedMemories) {
            names.add(Important.changeText("underline", memory.writeName()));
        }
        if (names.isEmpty()) {
            return Important.changeText("red", "Doposud nebyly posbírané žádné vzpomínky");
        }
        return String.join(", ", names);
    }

    public String writeDoneTasks() {
        ArrayList<String> names = new ArrayList<>();
        for (Task task : doneTasks) {
            names.add(task.getName());
        }
        if (names.isEmpty()) {
            return Important.changeText("red", "Doposud nebyly splněny žádné úkoly");
        }
        return String.join(", ", names);
    }

    /**
     * This method represents a system, which decides if the cutscene can be played.
     * @return true if canned, false if not.
     */
    public boolean canPlayCutscene() {
        if (cutscenes.peekCutscene() == null) {
            return false;
        }
        return this.collectedMemories.size() == cutscenes.peekCutscene().getRequiredMemories();
    }

    @Override
    public String toString() {
        return mode.executeInfo(this);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Mode getMode() {
        return mode;
    }

    /**
     * Helps {@link Game.CommandLoader} with deciding which mode player should have.
     * <p>
     *     When {@link #mode} is special, then {@link LocationMode} is returned in order to stop
     *     the command from executing.
     * </p>
     * Some commands can be done in more than one mode, but at the same time not in every mode.
     * So if the {@link #mode} is special, then those commands cannot be executed.
     * @return {@link #mode} if it is not special, {@link LocationMode} otherwise
     */
    public Mode specialGetMode() {
        if (!mode.isSpecial()) {
            return mode;
        }
        return new LocationMode();
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Location getPreviousLocation() {
        return previousLocation;
    }

    public void setPreviousLocation(Location previousLocation) {
        this.previousLocation = previousLocation;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }

    public ArrayList<Memory> getCollectedMemories() {
        return collectedMemories;
    }

    public void setCollectedMemories(ArrayList<Memory> collectedMemories) {
        this.collectedMemories = collectedMemories;
    }

    public ArrayList<Task> getDoneTasks() {
        return doneTasks;
    }

    public void setDoneTasks(ArrayList<Task> doneTasks) {
        this.doneTasks = doneTasks;
    }

    public CutsceneLoader getCutscenes() {
        return cutscenes;
    }

    public void setCutscenes(CutsceneLoader cutscenes) {
        this.cutscenes = cutscenes;
    }
}
