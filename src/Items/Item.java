package Items;

public class Item {


    private String name;
    private String description;
    private String code;
    private double weight;


    public Item(String name, String description, String code, double weight) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.weight = weight;
    }

    public Item() {
    }

    @Override
    public String toString() {
        //TODO toString metoda chybi
        return null;
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
    //Zatím žádné metody nepotřebujeme. Item slouží pouze jako datový kontejner (POJO)
}
