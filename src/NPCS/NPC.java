package NPCS;

/**
 * An abstract base class for NPCs, extended by:
 * <ul>
 *     <li>{@link FriendlyNPC}</li>
 *     <li>{@link EnemyNPC}</li>
 * </ul>
 * <p>
 * This class is a POJO.
 * </p>
 *
 * @author Matěj Pospíšil
 */
public abstract class NPC {


    protected String name;
    protected String position;
    protected String age;


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
