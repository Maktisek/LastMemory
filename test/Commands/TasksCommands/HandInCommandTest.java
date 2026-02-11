package Commands.TasksCommands;

import AroundPlayer.Player;
import Exceptions.WrongInitializationException;
import Items.Item;
import Items.Task;
import Locations.Location;
import NPCS.FriendlyNPC;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HandInCommandTest {

    Player player;


    @Test
    public void execute() {
        Location currentLoc = new Location();
        FriendlyNPC friendlyNPC = new FriendlyNPC();
        Task task = new Task();
        task.setCodesOfNeededObjects(new ArrayList<>(List.of("A")));
        task.setNamesOfNeededObjects(new ArrayList<>(List.of("a")));

        friendlyNPC.setTask(task);

        currentLoc.setFriendlyNPC(friendlyNPC);

        try {
            player = new Player(currentLoc);
        } catch (WrongInitializationException e) {
            fail();
        }


        Item item = new Item();
        item.setCode("A");
        item.setName("a");

        player.getInventory().addItem(item);

        player.setCurrentTask(player.getCurrentLocation().getFriendlyNPC().getTask());

        player.getCurrentTask().scanAndSolveTask(player);

        assertEquals(0, player.getInventory().getItems().size());
        assertEquals(0, player.getCurrentTask().getCodesOfNeededObjects().size());


    }
}