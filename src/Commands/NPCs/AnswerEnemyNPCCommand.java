package Commands.NPCs;

import AroundPlayer.Player;
import Commands.Command;
import Game.Important;
import Modes.LocationMode;

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
            Important.stopMusic(player.getCurrentLocation().getName());
            Important.playSound("right answer");
            return "Odpověď " + Important.changeText("underline", answer) + Important.changeText("green", " je správně!") +"\nLokace " + Important.changeText("underline", player.getCurrentLocation().getName()) + " je nyní otevřená";
        }
        this.inputWait = false;
        this.timeWait = true;
        Important.playSound("wrong sound");
        return Important.changeText("red", "Odpověď " + Important.changeText("underline", answer) + Important.changeText("red", " není správně"));
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
    public boolean IsTimeWaitAble() {
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
