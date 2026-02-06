package Commands.Items;

import AroundPlayer.Player;
import Items.Item;
import Locations.Location;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DropItemCommandTest {

    Player player;
    String name;
    DropItemCommand dropItemCommand;

    @Before
    public void setUp() throws Exception {
        name = "a";
    }

    @Test
    public void execute() {
        Location location = new Location();
        location.setItems(new ArrayList<>());

        Item item = new Item();
        item.setCode("A");
        item.setName("a");
        item.setWeight(4);

        player = new Player(location);
        player.getInventory().addItem(item);

        dropItemCommand = new DropItemCommand(player, name);

        dropItemCommand.execute();

        assertEquals(1, location.getItems().size());
        assertEquals(0, player.getInventory().getItems().size());
        assertNull(player.getInventory().getItems().get("A").get(0));
    }
}