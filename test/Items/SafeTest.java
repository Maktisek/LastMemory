package Items;

import Exceptions.WrongSafeCodeException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SafeTest {

    Safe safe1;

    @Before
    public void setUp() throws Exception {
        Item item = new Item();
        item.setName("Item1");
        item.setCode("Item1");
        item.setWeight(20);
        item.setDescription("aaaa");
        safe1 = new Safe("2",99,new ArrayList<>(List.of(item)));
    }

    @Test
    public void openSafe() {
        try {
            assertTrue(safe1.openSafe("R1;L2;R2"));
        }catch (WrongSafeCodeException e){
            fail();
        }
    }

    @Test
    public void dropItems() {
        Item item = new Item();
        item.setName("Item1");
        item.setCode("Item1");
        item.setWeight(20);
        item.setDescription("aaaa");
        ArrayList<Item> tempList = new ArrayList<>(List.of(item));
        assertEquals(tempList, safe1.dropItems());
    }

    @Test
    public void isLocked() {
        assertTrue(safe1.isLocked());
    }
}