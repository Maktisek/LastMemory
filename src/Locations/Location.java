package Locations;

import Items.Item;
import Items.Safe;
import NPCS.EnemyNPC;
import NPCS.FriendlyNPC;

import java.util.ArrayList;

public class Location {

    private String name;
    private String code;
    private String description;
    private EnemyNPC enemyNPC;
    private FriendlyNPC friendlyNPC;
    private ArrayList<Item> items;
    private Safe safe;
    private ArrayList<Location> possibleLocations;

    public Location(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.items = new ArrayList<>();
        this.possibleLocations = new ArrayList<>();
    }

    public boolean addEnemyNPC(EnemyNPC NPC){
        //TODO addEnemyNPC metoda chybi
        return true;
    }

    public boolean addFriendlyNPC(FriendlyNPC NPC){
        //TODO addFriendlyNPC metoda chybi
        return true;
    }

    public boolean addItem(Item item){
        //TODO addItem metoda chybi
        return true;
    }

    public Item findAndRemoveItem(String name){
        //TODO findAndRemoveItem metoda chybi
        //Najde a odevzda Item
        return null;
    }

    public boolean addSafe(Safe addSafe){
        //TODO addSafe metoda chybi
        return true;
    }

    public boolean addPossibleLocation(Location location){
        //TODO addPossibleLocation metoda chybi
        return true;
    }

    public Location findLocation(String name){
        //TODO findLocation metoda chybi
        //Projde vsechny mozne lokace a najde shodu jmen
        return null;
    }

    public boolean isPossible(){
        //TODO isPossible metoda chybi
        //Vrati zda muzeme do lokace vstoupit
        return true;
    }

    public boolean answerNPC(String answer){
        //TODO answerNPC metoda chybi
        //Odpovi na otazku NPC
        return true;
    }

    @Override
    public String toString() {
        //TODO toString metoda chybi
        return null;
    }
}
