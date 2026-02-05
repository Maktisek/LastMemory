package Items;

import java.util.Objects;

public class Item {


    private String name;
    private String description;
    private String code;
    private double weight;


    public Item() {
    }

    public Item(String name, double weight, String code, String description) {
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", code='" + code + '\'' +
                ", weight=" + weight +
                '}';
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
