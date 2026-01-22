package Commands;

public interface Command {

    /**
     * Executes the whole command operation.
     * @return the text information of the process.
     */
    String execute();

    /**
     * Returns if the command can stop the game
     * @return true if yes, false if not
     */
    boolean exit();

    /**
     * Returns if the game will wait for player's input after the command is done.
     * @return true if yes, false if not
     */
    boolean waitAble();

}
