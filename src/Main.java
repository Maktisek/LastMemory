import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ObjectMapper parser = new ObjectMapper();
        ArrayList<Bruh> bruhs = new ArrayList<>();

        try {
            InputStream input = new FileInputStream("res\\test.json");
            Bruh bruh = parser.readValue(input, Bruh.class);
            bruhs.add(bruh);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(bruhs.get(0));


    }
}