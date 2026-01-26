package Commands;

import AroundPlayer.Player;

public class ReadFriendlyNPCDescriptionCommand implements Command{

    private final Player player;

    public ReadFriendlyNPCDescriptionCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        if(player.getCurrentLocation().getFriendlyNPC() == null){
            return "V lokaci se nikdo nenachází";
        }
        return player.getCurrentLocation().getFriendlyNPC().writeDescription();
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public boolean waitAble() {
        return true;
    }

    @Override
    public boolean timeWaitAble() {
        return false;
    }

    @Override
    public boolean continuing() {
        return true;
    }

    @Override
    public void endAudio() {

    }
}
