package Items;

import java.util.Objects;

/**
 * Represents an individual item in the game.
 * <p>
 * This is a simple POJO class.
 * </p>
 *
 * @author Matěj Pospíšil
 */
public class Item {


    private String name;
    private String description;
    private String code;
    private double weight;


    public Item() {
    }


    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(code, item.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
