package NPCS;

/**
 * Represents the type of {@link FriendlyNPC}.
 * @author Matěj Pospíšil
 */
public enum TypeNPC {
    /**
     * Represents a {@link FriendlyNPC} who has a task and can give a memory to the player.
      */
    AKTIVNÍ,
    /**
     * Represents a {@link FriendlyNPC} who does not have a task and cannot give a memory to the player.
     */
    PASIVNÍ
}
