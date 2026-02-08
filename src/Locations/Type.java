package Locations;

/**
 * Represents the type of location.
 * @author Matěj Pospíšil
 */
public enum Type {
    /**
     * Represents a normal location.
     */
    LOCATION,
    /**
     * Represents a hallway location.
     */
    HALLWAY,

    /**
     * Represents a special location, which has to have a neighboring location with {@link Type#FADE} too in order to work properly.
     */
    FADE
}
