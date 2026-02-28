package Modes;

import java.io.Serializable;

/**
 * Represents the type of mode.
 * @author Matěj Pospíšil
 */
public enum ModeType implements Serializable {
    /**
     * Represents {@link BackpackMode}.
     */
    backpack,
    /**
     * Represents {@link IntroMode}.
     */
    intro,
    /**
     * Represents {@link LocationMode}.
     */
    location,
    /**
     * Represents {@link OutroMode}.
     */
    outro,
    /**
     * Represents {@link QuestionMode}.
     */
    question,
    /**
     * Represents {@link OptionsMode}.
     */
    options

}
