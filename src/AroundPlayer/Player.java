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
        return collectedMemories.add(memory);
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
        return doneTasks.add(task);
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
        this.mode = mode;
        return true;
    }

    public String writeMemories(){
        ArrayList<String> names = new ArrayList<>();
        for (Memory memory: collectedMemories){
            names.add(memory.getName());
        }
        if(names.isEmpty()){
            return "Doposud nebyly posbírané žádné vzpomínky";
        }
        return String.join(",",names);
    }

    public String writeDoneTasks(){
        ArrayList<String> names = new ArrayList<>();
        for (Task task: doneTasks){
            names.add(task.getName());
        }
        if(names.isEmpty()){
            return "Doposud nebyly splněny žádné úkoly";
        }
        return String.join(",",names);
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

    public static class Inventory{
        private final double capacity;
        private double weight;
        private HashMap<String, ArrayList<Item>> items;

        public Inventory() {
            this.capacity = 20.0;
            this.weight = 0;
            this.items = new HashMap<>();
        }

        public boolean checkAddCapacity(Item item){
            double temp = weight + item.getWeight();
            if(temp > capacity){
                return false;
            }else {
                this.weight = temp;
                return true;
            }
        }

        public boolean addItem(Item item){
            if (checkAddCapacity(item)) {
                if(items.containsKey(item.getCode())){
                    return items.get(item.getCode()).add(item);
                }else {
                    ArrayList<Item> temp = new ArrayList<>();
                    temp.add(item);
                    items.put(item.getCode(), temp);
                    return true;
                }
            }else {
                return false;
            }
        }

        public Item dropItem(String name){
            //TODO dropItem metoda chybi
            //Najde a odevzda Item
            return null;
        }

        public String writeItems(){
            ArrayList<String> names = new ArrayList<>(10);
            for (String key : items.keySet()){
                ArrayList<Item> temp = items.get(key);
                names.add(temp.size() + "x " +temp.get(0).getName());
            }
            if (names.isEmpty()){
                return "Batoh je prázdný";
            }
            return String.join(", ", names);
        }

        public double getCapacity() {
            return capacity;
        }

        public double getWeight() {
            return weight;
        }
    }
}
