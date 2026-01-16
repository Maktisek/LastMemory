import Locations.Location;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Location> locations = new ArrayList<>();
        try {
            InputStream input = new FileInputStream("res\\locations.json");
            Location[] location = mapper.readValue(input, Location[].class);
            locations.addAll(List.of(location));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       for (Location location: locations){
           System.out.println(location.toString());
       }

    }
}