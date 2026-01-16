package Items;

import AroundPlayer.Memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Task {

    private String name;
    private String description;
    private ArrayList<String> namesOfNeededObjects;
    private ArrayList<String> codesOfNeededObjects;
    private Memory memoryPrice;

    public Task(String name, String description, String[] namesOfNeededObjects, String[] codesOfNeededObjects, Memory memoryPrice) {
        this.name = name;
        this.memoryPrice = memoryPrice;
        this.codesOfNeededObjects = new ArrayList<>();
        this.namesOfNeededObjects = new ArrayList<>();
        this.description = description;
        this.namesOfNeededObjects.addAll(List.of(namesOfNeededObjects));
        this.codesOfNeededObjects.addAll(List.of(codesOfNeededObjects));
    }

    public Task() {
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
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", namesOfNeededObjects=" + namesOfNeededObjects +
                ", codesOfNeededObjects=" + codesOfNeededObjects +
                ", memoryPrice=" + memoryPrice +
                '}';
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
