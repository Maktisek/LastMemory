package Commands.Items;

import AroundPlayer.Player;
import Exceptions.WrongInitializationException;
import Game.Important;
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
        item.setName(name);
        item.setWeight(4);


        try {
            this.player = new Player(location);
        }catch (WrongInitializationException e){
            fail();
        }
        this.player.getInventory().checkAddCapacity(item);
        this.player.getInventory().addItem(item);



        dropItemCommand = new DropItemCommand(player, name);


        assertEquals("Položil si " + Important.changeText("underline", name) + " do " + player.getCurrentLocation().getName(),dropItemCommand.execute());

        assertEquals(0, player.getInventory().getItems().size());
        assertEquals(1, location.getItems().size());
    }
}