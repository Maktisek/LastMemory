package Commands;

import AroundPlayer.Player;
import Game.Important;
import Modes.LocationMode;

public class AnswerEnemyNPCCommand implements Command{

    private Player player;
    private String answer;
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
            Important.stopAudio("question mode");
            Important.playAudio(player.getCurrentLocation().getName());
            return "Odpověď " + answer + Important.changeText("green", " je správně!") +"\nLokace " + Important.changeText("underline", player.getCurrentLocation().getName()) + " je nyní otevřená";
        }
        this.inputWait = false;
        this.timeWait = true;
        return Important.changeText("red", "Odpověď " + Important.changeText("underline", answer) + Important.changeText("red", " není správně"));
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean waitAble() {
        return inputWait;
    }

    @Override
    public boolean timeWaitAble() {
        return timeWait;
    }
}
