package dev.saeedkhanloo.player;

import dev.saeedkhanloo.board.CyclicalBoard;
import dev.saeedkhanloo.board.field.Property;

/**
 * Greedy player type: always buys property if they have enough money.
 */
public class Greedy extends Player {

    /**
     * Constructs a Greedy player with the given name.
     * @param name The player's name
     */
    public Greedy(String name) {
        super(name);
    }

    /**
     * Greedy strategy: always buys property if possible.
     * @param board    The game board
     * @param property The property to consider buying
     */
    @Override
    public void strategy(CyclicalBoard board, Property property) {
        this.buy(board, property, this.money);
    }

}
