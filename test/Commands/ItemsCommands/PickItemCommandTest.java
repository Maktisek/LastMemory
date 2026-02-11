package Commands.ItemsCommands;

import AroundPlayer.Player;
import Exceptions.WrongInitializationException;
import Items.Item;
import Locations.Location;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PickItemCommandTest {

    Player player;
    String name;
    PickItemCommand pickItemCommand;

    @Before
    public void setUp() throws Exception {
        name = "a";
    }

    @Test
    public void execute() {
        Location currentLoc = new Location();

        Item item = new Item();
        item.setName(name);
        item.setCode("A");
        item.setWeight(4);

        currentLoc.setItems(new ArrayList<>(List.of(item)));

        try {
            player = new Player(currentLoc);
        }catch (WrongInitializationException e){
            fail();
        }

        pickItemCommand = new PickItemCommand(player, name);

        pickItemCommand.execute();

        assertEquals(1, player.getInventory().getItems().size());
        assertEquals(item, player.getInventory().getItems().get("A").get(0));
        assertEquals("a", player.getInventory().getItems().get("A").get(0).getName());
    }
}