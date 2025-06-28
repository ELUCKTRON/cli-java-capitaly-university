package dev.saeedkhanloo.player;

import dev.saeedkhanloo.board.CyclicalBoard;
import dev.saeedkhanloo.board.field.Property;

/**
 * Careful player type: only buys property if half of their money is enough.
 */
public class Careful extends Player {
    /**
     * Constructs a Careful player with the given name.
     * @param name The player's name
     */
    public Careful(String name) {
        super(name);
    }

    /**
     * Careful strategy: only buys property if half of their money is enough.
     * @param board    The game board
     * @param property The property to consider buying
     */
    @Override
    public void strategy(CyclicalBoard board, Property property) {
        int halfMoney = this.money / 2;
        this.buy(board, property, halfMoney);
    }
}
