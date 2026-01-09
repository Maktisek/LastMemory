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

    //Zatím žádné metody nepotřebujeme. Item slouží pouze jako datový kontejner (POJO)
}
