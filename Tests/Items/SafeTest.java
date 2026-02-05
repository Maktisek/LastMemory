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
        safe1 = new Safe("2",99,new ArrayList<>(List.of(new Item("Item1", 20, "Items1", "aaaa"))));
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
        ArrayList<Item> tempList = new ArrayList<>(List.of(new Item("Item1", 20, "Items1", "aaaa")));
        assertEquals(tempList, safe1.dropItems());
    }

    @Test
    public void isLocked() {
        assertTrue(safe1.isLocked());
    }
}