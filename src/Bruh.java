import java.util.ArrayList;

public class Bruh {


    private int age;
    private String name;
    private ArrayList<String> list;

    public Bruh() {
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bruh{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", list=" + list +
                '}';
    }
}
