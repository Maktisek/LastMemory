package Commands;

import AroundPlayer.Player;
import Modes.LocationMode;

public class AnswerEnemyNPCCommand implements Command{

    private Player player;
    private String answer;

    public AnswerEnemyNPCCommand(Player player, String answer) {
        this.player = player;
        this.answer = answer;
    }

    @Override
    public String execute() {
        if(player.getCurrentLocation().answerNPC(answer)){
            player.switchMode(new LocationMode());
            return "Odpověď " + answer + " je správně!\n Lokace " + player.getCurrentLocation().getName() + " je nyní otevřená";
        }
        return "Odpověď " + answer + " není správně";
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean waitAble() {
        return true;
    }
}
