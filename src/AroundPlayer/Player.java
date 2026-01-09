package AroundPlayer;

import Items.Item;
import Items.Task;
import Locations.Location;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {


    private Inventory inventory;
    private ArrayList<Memory> collectedMemories;
    private ArrayList<Task> doneTasks;
    private Task currentTask;
    private Location currentLocation;

    public Player(Location startLocation) {
        this.inventory = new Inventory();
        this.collectedMemories = new ArrayList<>();
        this.doneTasks = new ArrayList<>();
        this.currentTask = null;
        this.currentLocation = startLocation;
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
        //Zmeni lokaci
        return false;
    }

    public ArrayList<Location> scanAndAdd(){
        //TODO scanAndAdd metoda chybi
        //Ten arraylist je tady, proto aby se hracovi pak mohlo vypsat jake lokace se pridaly.
        //Prida nove lokace do lokace podle vzpominek
        return null;
    }

    @Override
    public String toString() {
        //TODO toString metoda chybi
        return null;
    }

    private static class Inventory{
        private final double capacity;
        private double weight;
        private HashMap<String, Item> items;

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
            //Odstrani item
            return null;
        }
    }
}
