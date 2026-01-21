package Commands;

public class ReadFriendlyNPCDescriptionCommand implements Command{
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
        return true;
    }
}
