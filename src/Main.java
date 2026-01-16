import Items.Task;
import Locations.Location;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Location> locations = new ArrayList<>();
        mapper.enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
        mapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            InputStream input = new FileInputStream("res\\test.json");
            Locations.Location location = mapper.readValue(input, Location.class);
            locations.add(location);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(locations.get(0));


    }
}