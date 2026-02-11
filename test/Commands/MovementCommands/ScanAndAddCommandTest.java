package Commands.MovementCommands;

import AroundPlayer.Memory;
import AroundPlayer.Player;
import Locations.Location;
import Modes.LocationMode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ScanAndAddCommandTest {


    Player player;
    ScanAndAddCommand scanAndAddCommand;

    @Before
    public void setUp() throws Exception {
        player = new Player();
    }

    @Test
    public void execute() {
        player.setMode(new LocationMode());

        Location location1 = new Location();
        location1.setName("a");
        location1.setCode("A");

        Location location2 = new Location();
        location2.setName("b");
        location2.setCode("B");

        Memory memory = new Memory();
        memory.setCode("A");

        memory.setLocationGift(location2);

        player.setCollectedMemories(new ArrayList<>());

        player.addMemory(memory);

        player.setCurrentLocation(location1);
        player.getCurrentLocation().setPossibleLocations(new ArrayList<>());

        scanAndAddCommand = new ScanAndAddCommand(player);

        scanAndAddCommand.execute();

        assertEquals(1, player.getCurrentLocation().getPossibleLocations().size());
    }
}