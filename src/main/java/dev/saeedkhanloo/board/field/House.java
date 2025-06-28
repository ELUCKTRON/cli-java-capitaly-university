package dev.saeedkhanloo.board.field;

import dev.saeedkhanloo.board.CyclicalBoard;
import dev.saeedkhanloo.player.Player;

/**
 * Represents a house property on the board.
 * If the owner is null (e.g., previous owner went bankrupt), a player can buy it based on their strategy.
 * If another player owns it, rent must be paid; if the player can't pay, they go bankrupt.
 */
public class House extends Property {

    /**
     * Constructs a House with the given ID.
     * @param id The position of the house on the board
     */
    public House(int id) {
        super(id);
    }

    /**
     * Handles the action when a player lands on this field.
     * @param board  The game board
     * @param player The player taking the action
     */
    @Override
    public void action(CyclicalBoard board, Player player) {
        if (this.getOwner() == null) {
            player.strategy(board, this);
        } else if (!this.getOwner().equals(player)) {
            if (player.getMoney() < RENT_HOUSE_PRICE) {
                player.gameOver(board);
            } else {
                player.decreaseMoney(Property.RENT_HOUSE_PRICE);
                this.getOwner().increaseMoney(Property.RENT_HOUSE_PRICE);
            }
        }
    }

}
