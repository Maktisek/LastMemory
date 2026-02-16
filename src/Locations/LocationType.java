package Locations;

/**
 * Represents the type of location.
 * @author Matěj Pospíšil
 */
public enum LocationType {
    /**
     * Represents a normal location.
     */
    LOCATION,
    /**
     * Represents a hallway location.
     */
    HALLWAY,

    /**
     * Represents a special location, which has to have a neighboring location with {@link LocationType#FADE} too in order to work properly.
     */
    FADE,
    /**
     * Represents an elevator.
     * Used for sorting possible locations in the correct order (always behind hallways)
     */
    ELEVATOR
}
