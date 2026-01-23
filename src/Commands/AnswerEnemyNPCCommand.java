package Commands;

import AroundPlayer.Player;

public class AnswerEnemyNPCCommand implements Command{

    private Player player;

    @Override
    public String execute() {
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean waitAble() {
        return false;
    }
}
