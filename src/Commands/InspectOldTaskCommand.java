package Commands;

import AroundPlayer.Player;

public class InspectOldTaskCommand implements Command {

    private Player player;
    private String name;

    public InspectOldTaskCommand(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    @Override
    public String execute() {
        return player.writeOldTask(name);
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
