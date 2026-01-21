package Commands;

public class ExitCommand implements Command{
    @Override
    public String execute() {
        return "Hra se vypíná";
    }

    @Override
    public boolean exit() {
        return true;
    }

    @Override
    public boolean waitAble() {
        return false;
    }
}
