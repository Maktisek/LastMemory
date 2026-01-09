package Game;

import AroundPlayer.Memory;
import AroundPlayer.Player;
import Items.Item;
import Items.Safe;
import Items.Task;
import Locations.Location;
import NPCS.EnemyNPC;
import NPCS.FriendlyNPC;

import java.util.ArrayList;

public class Initialization {


    ArrayList<Item> items;
    ArrayList<Safe> safes;
    ArrayList<Memory> memories;
    ArrayList<Task> tasks;
    ArrayList<EnemyNPC> enemyNPCS;
    ArrayList<FriendlyNPC> friendlyNPCS;
    ArrayList<Location> sideLocations;
    ArrayList<Location> mainLocations;
    Player player;

    public Initialization() {
        this.items = new ArrayList<>();
        this.safes = new ArrayList<>();
        this.memories = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.enemyNPCS = new ArrayList<>();
        this.friendlyNPCS = new ArrayList<>();
        this.sideLocations = new ArrayList<>();
        this.mainLocations = new ArrayList<>();
    }

    public void loadItems(){
        //TODO loadItems metoda chybi
    }
    public void loadSafes(){
        //TODO loadSafes metoda chybi
    }
    public void loadMemories(){
        //TODO loadMemories metoda chybi
    }
    public void loadTasks(){
        //TODO loadTasks metoda chybi
    }
    public void loadEnemyNPCS(){
        //TODO loadEnemyNPCS metoda chybi
    }
    public void loadFriendlyNPCS(){
        //TODO loadFriendlyNPCS metoda chybi
    }
    public void loadSideLocations(){
        //TODO loadSideLocations metoda chybi
    }
    public void loadMainLocations(){
        //TODO loadMainLocations metoda chybi
    }
    public void connectMainLocations(){
        //TODO connectMainLocations metoda chybi
    }
    public void loadPlayerAndConnectOthers(){
        //TODO loadPlayerAndConnectOthers metoda chybi
    }
}
