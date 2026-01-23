package Commands;

/**
 * Nejsem si jistý, zda to zatím potřebuji
 */
public class CheckTaskCommand implements Command{
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
