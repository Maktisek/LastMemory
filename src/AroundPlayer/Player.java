package AroundPlayer;

import Items.Item;
import Items.Task;
import Locations.Location;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {


    private Inventory inventory;
    private ArrayList<Memory> CollectedMemories;
    private ArrayList<Task> doneTasks;
    private Task currentTask;
    private Location currentLocation;

    public Player(ArrayList<Memory> memories, ArrayList<Task> doneTasks, Task currentTask, Location currentLocation) {
        this.inventory = new Inventory();
        this.CollectedMemories = memories;
        this.doneTasks = doneTasks;
        this.currentTask = currentTask;
        this.currentLocation = currentLocation;
    }

    public boolean addMemory(Memory memory){
        //TODO addMemory metoda chybi
        return true;
    }

    public void sortMemories(){
        //TODO sortMemories metoda chybi
    }

    public boolean addDoneTask(Task task){
        //TODO addDoneTask metoda chybi
        return true;
    }

    public Task findTask(String name){
        //TODO findTask metoda chybi
        return null;
    }

    public boolean switchLocation(Location location){
        //TODO switchLocation metoda chybi
        return false;
    }

    public boolean addCurrentTask(Task task){
        //TODO addCurrentTask metoda chybi
        return false;
    }

    public ArrayList<Location> scanAndAdd(){
        //TODO scanAndAdd metoda chybi
        //Ten arraylist je tady, proto aby se hracovi pak mohlo vypsat jako lokace se pridaly.
        return null;
    }


    private static class Inventory{
        private final double capacity;
        private HashMap<String, Item> items;

        public Inventory() {
            this.capacity = 20;
            this.items = new HashMap<>();
        }

        public boolean addItem(Item item){
            //TODO addItem metoda chybi
            return false;
        }

        public Item dropItem(String name){
            //TODO dropItem metoda chybi
            return null;
        }
    }
}
