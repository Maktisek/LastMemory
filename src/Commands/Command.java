package Commands;
/**
 * Defines a command that can be executed by the player.
 * @author Matěj Pospíšil
 */
public interface Command {

    /**
     * Executes the command.
     * @return the text information of the process.
     */
    String execute();

    /**
     * Returns if the command should close the game.
     * @return true if yes, false if not
     */
    boolean exit();

    /**
     * Returns if the game should wait for player's input after the command is done.
     * <p>
     * This system helps the player to read some longer texts without any interference.
     * @return true if the console should wait for the player`s input, false otherwise.
     */
    boolean isWaitAble();

    /**
     * Returns if the game should wait for a specific time after the command is done.
     * <p>
     * This system helps the console to be a lot cleaner.
     * @return true if the system should wait, false otherwise.
     */
    boolean IsTimeWaitAble();

    /**
     * Returns if the process of processing all commands should continue or stop.
     * This can be used when more than one command is executed after one player's  input.
     * @return true if the chain of commands should continue, false if not.
     */
    boolean isContinuing();


    /**
     * Stops all played audios in {@link #execute()} if it is necessary.
     */
    void endAudio();

}
