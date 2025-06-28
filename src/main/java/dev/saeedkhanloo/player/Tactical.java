package dev.saeedkhanloo.player;

import dev.saeedkhanloo.board.CyclicalBoard;
import dev.saeedkhanloo.board.field.Property;

/**
 * Tactical player type: buys property every second opportunity (alternates buying).
 */
public class Tactical extends Player {

    /**
     * Counter to track rounds for tactical buying.
     */
    private int roundCounter;

    /**
     * Constructs a Tactical player with the given name.
     * @param name The player's name
     */
    public Tactical(String name) {
        super(name);
        roundCounter = 0;
    }

    /**
     * Tactical strategy: buys property every second opportunity.
     * @param board    The game board
     * @param property The property to consider buying
     */
    @Override
    public void strategy(CyclicalBoard board, Property property) {
        roundCounter++;
        if (roundCounter % 2 == 1) {
            this.buy(board, property, this.money);
        }
    }
}
