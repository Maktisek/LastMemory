package Modes;
/**
 * Represents the type of mode.
 * @author Matěj Pospíšil
 */
public enum ModeType {
    /**
     * Represents {@link BackpackMode}.
     * Some commands cannot be done in that mode.
     */
    backpack,
    /**
     * Represents {@link IntroMode}.
     * Most of the commands cannot be done in that mode, since that mode
     * is a special mode.
     */
    intro,
    /**
     * Represents {@link LocationMode}.
     * Some commands cannot be done in that mode.
     */
    location,
    /**
     * Represents {@link OutroMode}.
     * Most of the commands cannot be done in that mode, since that mode
     * is a special mode.
     */
    outro,
    /**
     * Represents {@link QuestionMode}.
     * Most of the commands cannot be done in that mode, since that mode
     * is a special mode.
     */
    question

}
