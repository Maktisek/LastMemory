package Items;

import AroundPlayer.Memory;
import Game.Important;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Task {

    private String name;
    private String description;
    private ArrayList<String> namesOfNeededObjects;
    private ArrayList<String> codesOfNeededObjects;
    private Memory memoryPrice;


    public Task() {
    }

    public ArrayList<String> scanAndSolveTask(HashMap<String, ArrayList<Item>> input){
        //TODO scanAndSolveTask metoda chybi
        //Projde input a odevzda ty predmety, ktere ma.
        //Vraci arraylist nazvu itemu, aby hrac videl, co odevzdal.
        //Resi celou logiku vyhodnocovani ukolu
        return null;
    }

    public boolean isDone(){
        //TODO isDone metoda chybi
        //Podiva se, zda nahodou neni ukol uz hotovy
        return true;
    }

    public Memory giveMemory(){
        //TODO giveMemory metoda chybi
        //Odevzda vypominku a nastavi si ji na null.
        return null;
    }

    public String writeAllNeededItems(){
        return String.join(", ", namesOfNeededObjects);
    }

    @Override
    public String toString() {
        return "--------------------Úkol: "+this.name+"-------------------- \n"+
                Important.writeLongTexts(this.description) +
                "Zbývající potřebné předměty: " + writeAllNeededItems();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getCodesOfNeededObjects() {
        return codesOfNeededObjects;
    }

    public void setCodesOfNeededObjects(ArrayList<String> codesOfNeededObjects) {
        this.codesOfNeededObjects = codesOfNeededObjects;
    }

    public Memory getMemoryPrice() {
        return memoryPrice;
    }

    public void setMemoryPrice(Memory memoryPrice) {
        this.memoryPrice = memoryPrice;
    }

    public ArrayList<String> getNamesOfNeededObjects() {
        return namesOfNeededObjects;
    }

    public void setNamesOfNeededObjects(ArrayList<String> namesOfNeededObjects) {
        this.namesOfNeededObjects = namesOfNeededObjects;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
