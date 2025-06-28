package dev.saeedkhanloo.board.field;

import dev.saeedkhanloo.board.CyclicalBoard;
import dev.saeedkhanloo.player.Player;

/**
 * Represents an empty land property on the board.
 * If the land is unowned, the player can decide to buy it based on their strategy.
 * If the player owns it, they can build a house if they have enough money; otherwise, they go bankrupt.
 * If another player owns it, rent must be paid; if the player can't pay, they go bankrupt.
 */
public class EmptyLand extends Property {

    /**
     * Constructs an EmptyLand with the given ID.
     * @param id The position of the land on the board
     */
    public EmptyLand(int id) {
        super(id);
    }

    /**
     * Handles the action when a player lands on this field.
     * @param board  The game board
     * @param player The player taking the action
     */
    @Override
    public void action(CyclicalBoard board, Player player) {
        player.setPosition(this.id);

        if (this.getOwner() == null) {
            player.strategy(board, this);
        } else if (this.getOwner().equals(player)) {
            if (player.getMoney() < BUILD_PRICE) {
                player.gameOver(board);
            } else {
                player.decreaseMoney(Property.BUILD_PRICE);
                board.increaseBank(Property.BUILD_PRICE);

                House house = new House(this.id);
                house.setOwner(this.getOwner());
                board.building(house);
                player.updateProperty(this, house);
            }
        } else {
            if (player.getMoney() < RENT_EMPTY_PRICE) {
                player.gameOver(board);
            } else {
                player.decreaseMoney(Property.RENT_EMPTY_PRICE);
                this.getOwner().increaseMoney(Property.RENT_EMPTY_PRICE);
            }
        }
    }
}
