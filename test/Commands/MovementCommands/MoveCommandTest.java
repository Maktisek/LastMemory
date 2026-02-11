package Commands.MovementCommands;

import AroundPlayer.Player;
import Game.Important;
import Locations.Location;
import Modes.LocationMode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MoveCommandTest {

    MoveCommand moveCommand;
    Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player();
    }

    @Test
    public void execute() {
        player.setMode(new LocationMode());

        Location location1 = new Location();
        Location location2 = new Location();

        location1.setName("a");
        location2.setName("b");

        location1.setPossibleLocations(new ArrayList<>(List.of(location2)));
        location2.setPossibleLocations(new ArrayList<>(List.of(location1)));

        player.setCurrentLocation(location1);

        String name = "b";
        moveCommand = new MoveCommand(player, name);

        assertEquals(Important.changeText("green", "Přesouváš se do: " + name), moveCommand.execute());
        assertEquals(name, player.getCurrentLocation().getName());
    }
}