package Items;

import java.util.ArrayList;

public class Safe{


    private ArrayList<Item> items;
    private String code;
    private int startPointer;
    private int currentPointer;

    public Safe(String code, int startPointer) {
        this.items = new ArrayList<>();
        this.code = code;
        this.startPointer = startPointer;
        this.currentPointer = startPointer;
    }

    public boolean openSafe(String code){
        //TODO openSafe metoda chybi
        //Zde se bude nachazet cela logika otevirani safu. Pokud budu potreboval dalsi metodu, tak ji pridam pozdeji
        return false;
    }

    public ArrayList<Item> dropItems(){
        //TODO dropItems metoda chybi
        //Vysype vsechny predmety do lokace.
        return null;
    }

    public String safeContent(){
        //TODO safeContent metoda chybi
        //Vypise vsechny predmety, ktere se v safu nachazi (uzitecne pri otevreni safu)
        return null;
    }

    public void resetCurrentPointer(){
        //TODO resetCurrentPointer metoda chybi
        //Resetuje pozici pointeru na startPointer
    }

    private boolean isLocked(){
        //TODO isLocked metoda chybi
        //Pomoci isEmpty() zkontroluje, zda je safe jeste zamknuty
        return true;
    }

}
