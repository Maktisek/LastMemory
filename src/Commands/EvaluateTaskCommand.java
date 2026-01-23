package Commands;

import AroundPlayer.Player;

public class EvaluateTaskCommand implements Command{

    private Player player;

    public EvaluateTaskCommand(Player player) {
        this.player = player;
    }

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
