package Commands.NPCs;

import AroundPlayer.Player;
import Exceptions.WrongInitializationException;
import Locations.Location;
import Modes.LocationMode;
import Modes.QuestionMode;
import NPCS.EnemyNPC;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AnswerEnemyNPCCommandTest {

    Player player;
    String answer;
    AnswerEnemyNPCCommand answerEnemyNPCCommand;


    @Before
    public void setUp() throws Exception {
        this.answer = "a, b";
    }

    @Test
    public void execute() {
        Location loc = new Location();

        EnemyNPC enemyNPC = new EnemyNPC();

        enemyNPC.setPossibleAnswers(new ArrayList<>(List.of("a, b")));
        enemyNPC.setQuestion("Prvni dve pismena abecedy");

        loc.setEnemyNPC(enemyNPC);


        try {
            player = new Player(loc);
        }catch (WrongInitializationException e){
            fail();
        }

        player.setMode(new QuestionMode());

        answerEnemyNPCCommand = new AnswerEnemyNPCCommand(player, answer);

        answerEnemyNPCCommand.execute();

        assertEquals(new LocationMode().getInfo(), player.getMode().getInfo());

    }
}