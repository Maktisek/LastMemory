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

    public NPC() {
    }

    public String writeDescription(){
        return position + ": " + name + ", " + age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
