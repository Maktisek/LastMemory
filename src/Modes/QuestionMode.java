package Modes;

import AroundPlayer.Player;
import Game.Important;

/**
 * A mode responsible for handling an {@link NPCS.EnemyNPC} encounter.
 *
 * @author Matěj Pospíšil
 */
public class QuestionMode implements Mode{

    @Override
    public String executeInfo(Player player) {
        return player.getCurrentLocation().getEnemyNPC().toString();
    }

    @Override
    public String executeHelp() {
        return Important.readTxtFiles("/TextFiles/questionHelp.txt", 0);
    }

    @Override
    public ModeType getInfo() {
        return ModeType.question;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

}
