import java.net.URL;

public class ResourceTester {
    public static void main(String[] args) {
        // cesta k souboru, který chceš otestovat
        String pathName = "/jsons/sidelocations.json";

        // najde resource relativně ke třídě ResourceTester
        URL resource = ResourceTester.class.getResource(pathName);

        if (resource == null) {
            System.out.println("❌ Resource nenalezen: " + pathName);
        } else {
            System.out.println("✅ Resource nalezen: " + resource);
        }
    }
}
