package dev.saeedkhanloo.board.field;

import dev.saeedkhanloo.board.CyclicalBoard;
import dev.saeedkhanloo.player.Player;

/**
 * Abstract class for properties that can be owned (e.g., House, EmptyLand).
 * Properties can change type (e.g., EmptyLand to House) based on gameplay.
 */
public abstract class Property extends Field {

    /**
     * The price to build a house on an empty land.
     */
    public static final int BUILD_PRICE = 4000;
    /**
     * The price to buy a property.
     */
    public static final int BUY_PRICE = 1000;
    /**
     * The rent price for a house.
     */
    public static final int RENT_HOUSE_PRICE = 2000;
    /**
     * The rent price for an empty land.
     */
    public static final int RENT_EMPTY_PRICE = 500;

    /**
     * The owner of the property (null if unowned).
     */
    private Player owner;

    /**
     * @return the owner of the property
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the property.
     * @param owner The player who owns the property
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Constructs a property with the given ID.
     * @param id The position of the property on the board
     */
    public Property(int id) {
        super(id);
        owner = null;
    }

    /**
     * Defines the action to be performed when a player lands on this property.
     * @param board  The game board
     * @param player The player taking the action
     */
    @Override
    public abstract void action(CyclicalBoard board, Player player);
}
