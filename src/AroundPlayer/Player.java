package AroundPlayer;

import Items.Item;
import Items.Task;
import Locations.Location;
import Modes.LocationMode;
import Modes.Mode;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {


    private Inventory inventory;
    private ArrayList<Memory> collectedMemories;
    private ArrayList<Task> doneTasks;
    private Task currentTask;
    private Location currentLocation;
    private Location previousLocation;
    private Mode mode;

    public Player(Location startLocation) {
        this.inventory = new Inventory();
        this.collectedMemories = new ArrayList<>();
        this.doneTasks = new ArrayList<>();
        this.currentTask = null;
        this.currentLocation = startLocation;
        this.previousLocation = null;
        this.mode = new LocationMode();
    }

    public boolean addMemory(Memory memory){
        //TODO addMemory metoda chybi
        //Prida vzpominku
        return true;
    }

    public void sortMemories(){
        //TODO sortMemories metoda chybi
        //Seradi vzpominky podle boolean
    }

    public String writeMemory(String name){
        //TODO writeMemory chybi
        //Vypise pozadovanou vzpominku
        return null;
    }

    public boolean addDoneTask(Task task){
        //TODO addDoneTask metoda chybi
        //Prida hotovy ukol
        return true;
    }

    public String writeTask(String name){
        //TODO findTask metoda chybi
        //Vypise pozadovany ukol
        return null;
    }

    public boolean addCurrentTask(Task task){
        //TODO addCurrentTask metoda chybi
        //Prida aktualni ukol
        return false;
    }

    public boolean deleteTask(){
        //TODO deleteTask metoda chybi - pozor currentTask se presune do doneTasks
        //Odstrani aktualni ukol
        return false;
    }

    public boolean switchLocation(Location location){
        //TODO switchLocation metoda chybi
        //Zmeni lokaci a nastavi na previousLocation currentLocation.
        return false;
    }

    public boolean runAway(){
        //TODO runAway metoda chybi
        //Nastavi na currentLocation previousLocation, ale na previous location nastavi null
        return true;
    }

    public ArrayList<Location> scanAndAddPossibleLocations(){
        //TODO scanAndAddPossibleLocation metoda chybi
        //Ten arraylist je tady, proto aby se hracovi pak mohlo vypsat jake lokace se pridaly.
        //Prida nove lokace do lokace podle vzpominek
        return null;
    }

    public boolean canEnd(){
        //TODO canEnd metoda chybi
        //Posoudi zda ma hrac uz 10 vzpominek
        return true;
    }

    public boolean switchMode(Mode mode){
        //TODO switchMode metoda chybi
        //Zmeni mode
        return true;
    }

    @Override
    public String toString() {
        //TODO toString metoda chybi
        //Bude pouzivat mode
        return null;
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

    private static class Inventory{
        private final double capacity;
        private double weight;
        private HashMap<String, ArrayList<Item>> items;

        public Inventory() {
            this.capacity = 20.0;
            this.weight = 0;
            this.items = new HashMap<>();
        }

        public boolean addItem(Item item){
            //TODO addItem metoda chybi
            //Prida item
            return false;
        }

        public Item dropItem(String name){
            //TODO dropItem metoda chybi
            //Najde a odevzda Item
            return null;
        }
    }
}
