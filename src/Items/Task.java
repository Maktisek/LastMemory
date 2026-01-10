package Items;

import AroundPlayer.Memory;

import java.util.ArrayList;
import java.util.HashMap;

public class Task {

    private String name;
    private String description;
    private ArrayList<String> namesOfNeededObjects;
    private ArrayList<String> codesOfNeededObjects;
    private Memory memoryPrice;

    public Task(String name, String description, Memory memoryPrice) {
        this.name = name;
        this.memoryPrice = memoryPrice;
        this.codesOfNeededObjects = new ArrayList<>();
        this.namesOfNeededObjects = new ArrayList<>();
        this.description = description;
    }

    public boolean addNameOfNeededObject(){
        //TODO addNameOfNeededObject metoda chybi
        return true;
    }

    public boolean addCodeOfNeededObject(){
        //TODO addCodeOfNeededObject metoda chybi
        return true;
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

    @Override
    public String toString() {
        //TODO toString metoda chybi
        return null;
    }

    public String getName() {
        return name;
    }
}
