package Locations;

import Items.Item;
import Items.Safe;
import NPCS.EnemyNPC;
import NPCS.FriendlyNPC;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Location {

    private String name;
    private String code;
    private String description;
    private EnemyNPC enemyNPC;
    private FriendlyNPC friendlyNPC;
    private ArrayList<Item> items;
    private Safe safe;
    private ArrayList<Location> possibleLocations;

    public Location() {
    }

    public boolean addEnemyNPC(EnemyNPC NPC) {
        //TODO addEnemyNPC metoda chybi
        return true;
    }

    public boolean addFriendlyNPC(FriendlyNPC NPC) {
        //TODO addFriendlyNPC metoda chybi
        return true;
    }

    public boolean addItem(Item item) {
        //TODO addItem metoda chybi
        return true;
    }

    public Item findAndRemoveItem(String name) {
        //TODO findAndRemoveItem metoda chybi
        //Najde a odevzda Item
        return null;
    }

    public boolean addSafe(Safe addSafe) {
        //TODO addSafe metoda chybi
        return true;
    }

    public boolean tryOpenSafe(String code) {
        //TODO tryCodeSafe metoda chybi
        return true;
    }

    public ArrayList<String> extractSafe() {
        //TODO addItems metoda chybi
        //Prida vsechny itemy ze safu do listu itemu
        return null;
    }

    public boolean addPossibleLocation(Location location) {
        return possibleLocations.add(location);
    }

    public Location findLocation(String name) {
        for (Location location: possibleLocations){
            if (location.getName().equalsIgnoreCase(name)) {
                return location;
            }
        }
        return null;
    }

    public boolean isPossible() {
        //TODO isPossible metoda chybi
        //Vrati zda muzeme v lokaci operovat
        return true;
    }

    public boolean answerNPC(String answer) {
        //TODO answerNPC metoda chybi
        //Odpovi na otazku NPC
        return true;
    }

    public String writeItemsNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Item item : items) {
            names.add(item.getName());
        }
        if (names.isEmpty()) {
            return "Místnost je prázdná";
        } else {
            return String.join(", ", names);
        }
    }

    public String writeAllPossibleLocations() {
        ArrayList<String> names = new ArrayList<>(25);
        for (Location location : possibleLocations) {
            names.add(location.getName());
        }
        return String.join(",", names);
    }

    public String writeFriendlyNPCName() {
        if (friendlyNPC != null) {
            return this.friendlyNPC.getName();
        } else {
            return "Nikdo se zde nenachází";
        }
    }

    public String writeSafe() {
        if (safe != null) {
            return "Přítomný";
        } else {
            return "Nepřítomný";
        }
    }

    public String testNames(){
        //TODO tahle metoda je jen pro test
        Random random = new Random();
        return possibleLocations.get(random.nextInt(0, possibleLocations.size())).getName();
    }


    @Override
    public String toString() {
        return "------------------" + this.name + "------------------ \n" +
                "Postava: " + writeFriendlyNPCName() + "\n" +
                "Předměty: " + writeItemsNames() + "\n" +
                "Safe: " + writeSafe() + "\n" +
                "Další možné místnosti: " + writeAllPossibleLocations();

    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Location> getPossibleLocations() {
        return possibleLocations;
    }

    public void setPossibleLocations(ArrayList<Location> possibleLocations) {
        this.possibleLocations = possibleLocations;
    }

    public Safe getSafe() {
        return safe;
    }

    public void setSafe(Safe safe) {
        this.safe = safe;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public EnemyNPC getEnemyNPC() {
        return enemyNPC;
    }

    public void setEnemyNPC(EnemyNPC enemyNPC) {
        this.enemyNPC = enemyNPC;
    }

    public FriendlyNPC getFriendlyNPC() {
        return friendlyNPC;
    }

    public void setFriendlyNPC(FriendlyNPC friendlyNPC) {
        this.friendlyNPC = friendlyNPC;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
