package NPCS;

public abstract class NPC {


    protected String name;
    protected String position;
    protected String age;

    public NPC(String name, String position, String age) {
        this.name = name;
        this.position = position;
        this.age = age;
    }
}
