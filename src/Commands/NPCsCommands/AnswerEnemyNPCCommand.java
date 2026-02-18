package Commands.NPCsCommands;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;
import Modes.LocationMode;

/**
 * Represents a command, which answers {@link NPCS.EnemyNPC} question.
 * <p>
 *     {@link #answer} represents the answer.
 * </p>
 * If the answer is wrong, then the {@link #inputWait} is set to false, {@link #timeWait} is set to true
 * and a message is returned indicating that the answer was incorrect.
 * Otherwise, the player’s mode is set to {@link LocationMode} and a message is returned indicating that the answer was correct.
 * @author Matěj Pospíšil
 */
public class AnswerEnemyNPCCommand implements Command {

    private final Player player;
    private final String answer;
    private boolean inputWait;
    private boolean timeWait;

    public AnswerEnemyNPCCommand(Player player, String answer) {
        this.player = player;
        this.answer = answer;
        this.inputWait = true;
        this.timeWait = false;
    }

    @Override
    public String execute() {
        if(player.getCurrentLocation().answerNPC(answer)){
            player.switchMode(new LocationMode());
            Important.stopMusic("question mode");
            Important.playSound("right answer");
            return Important.writeSpace(60)+"Odpověď " + Important.changeText("underline", answer) + Important.changeText("green", " je správně!") +"\nLokace " + Important.changeText("underline", player.getCurrentLocation().getName()) + " je nyní otevřená.";
        }
        this.inputWait = false;
        this.timeWait = true;
        Important.playSound("wrong sound");
        return Important.writeSpace(60)+Important.changeText("red", "Odpověď " + Important.changeText("underline", answer) + Important.changeText("red", " není správně"));
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean isWaitAble() {
        return inputWait;
    }

    @Override
    public boolean isTimeWaitAble() {
        return timeWait;
    }

    @Override
    public boolean isContinuing() {
        return true;
    }


    @Override
    public void endAudio() {

    }
}
