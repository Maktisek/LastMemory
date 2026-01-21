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
        if(location != null){
            previousLocation = currentLocation;
            currentLocation = location;
            return true;
        }
        return false;
    }

    public boolean runAway(){
       if(previousLocation != null){
           currentLocation = previousLocation;
           previousLocation = null;
           return true;
       }
       else return false;
    }

    public String scanAndAddPossibleLocations() {
        ArrayList<String> names = new ArrayList<>();
        for (Memory memory : collectedMemories) {
            if (memory.getLocationGift() != null && memory.getCode().equalsIgnoreCase(currentLocation.getCode())) {
                currentLocation.addPossibleLocation(memory.getLocationGift());
                memory.getLocationGift().addPossibleLocation(currentLocation);
                names.add(memory.getLocationGift().getName());
                memory.setLocationGift(null);
            }
            if (!names.isEmpty()) {
                return "Nové odemklé lokace: " + String.join(", ", names);
            }
        }
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
        return String.join(", ",names);
    }

    public String writeDoneTasks(){
        ArrayList<String> names = new ArrayList<>();
        for (Task task: doneTasks){
            names.add(task.getName());
        }
        if(names.isEmpty()){
            return "Doposud nebyly splněny žádné úkoly";
        }
        return String.join(", ",names);
    }

    public boolean isFree(){
        return currentLocation.getEnemyNPC() == null;
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

        public boolean checkDropCapacity(Item item){
            double temp = weight - item.getWeight();
            if(temp < 0){
                return false;
            }else {
                this.weight = temp;
            }
            return true;
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

        public Item dropItem(String name) {
                for (String key : items.keySet()) {
                    if (items.containsKey(key) && !items.get(key).isEmpty() && items.get(key).get(0).getName().equalsIgnoreCase(name)) {
                        Item result = items.get(key).get(0);
                        if (checkDropCapacity(result)) {
                            items.get(key).remove(0);
                            return result;
                        }
                    }
                }
                return null;
            }

        public String writeItems(){
            ArrayList<String> names = new ArrayList<>(10);
            for (String key : items.keySet()) {
                ArrayList<Item> temp = items.get(key);
                if (!temp.isEmpty()) {
                    names.add(temp.size() + "x " + temp.get(0).getName());
                }
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
