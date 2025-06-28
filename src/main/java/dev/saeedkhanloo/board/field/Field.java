package dev.saeedkhanloo.board.field;

import dev.saeedkhanloo.board.CyclicalBoard;
import dev.saeedkhanloo.player.Player;

/**
 * Abstract class for all different types of fields on the board.
 * Each field has an ID (position) and must implement the action method.
 */
public abstract class Field {

    /**
     * The position of the field on the board.
     */
    protected int id;

    /**
     * @return the field's position ID
     */
    public int getId() {
        return id;
    }

    /**
     * Constructs a field with the given ID.
     * @param id The position of the field on the board
     */
    public Field(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID CANNOT BE NEGATIVE");
        }
        this.id = id;
    }

    /**
     * Defines the action to be performed when a player lands on this field.
     * @param board  The game board
     * @param player The player taking the action
     */
    public abstract void action(CyclicalBoard board, Player player);
}
