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

    public String scanAndSolveTask(HashMap<String, ArrayList<Item>> input){
        ArrayList<String> temp = new ArrayList<>();
        for (String key: input.keySet()){
            if(codesOfNeededObjects.contains(key)){
                temp.add(namesOfNeededObjects.get(codesOfNeededObjects.indexOf(key)));
                input.get(key).remove(0);
                codesOfNeededObjects.remove(key);
            }
        }
        if(!temp.isEmpty()) {
            return "Odevzdané předměty: " + String.join(", " + temp);
        }
        return "Nelze odevzdat žádný předmět";
    }

    public boolean isDone(){
        return codesOfNeededObjects.isEmpty();
    }

    public Memory giveMemory(){
        Memory temp = memoryPrice;
        this.memoryPrice = null;
        return temp;
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
