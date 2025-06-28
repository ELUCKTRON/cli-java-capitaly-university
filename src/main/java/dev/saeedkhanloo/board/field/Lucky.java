package dev.saeedkhanloo.board.field;

import dev.saeedkhanloo.board.CyclicalBoard;
import dev.saeedkhanloo.player.Player;

/**
 * Represents a lucky field on the board. When a player lands here, they receive money from the bank.
 * The amount is determined by luckPoint, but if the bank doesn't have enough, the player gets whatever is left.
 */
public class Lucky extends Field {

    /**
     * The amount of money a player receives when landing on this field.
     */
    private final int luckPoint;

    /**
     * Constructs a Lucky field with the given ID and luck point value.
     * @param id        The position of the field on the board
     * @param luckPoint The amount of money to be received
     */
    public Lucky(int id, int luckPoint) {
        super(id);
        if (luckPoint < 0) {
            throw new IllegalArgumentException("luck point needs to be a positive number");
        }
        this.luckPoint = luckPoint;
    }

    /**
     * Handles the action when a player lands on this field.
     * @param board  The game board
     * @param player The player taking the action
     */
    @Override
    public void action(CyclicalBoard board, Player player) {
        player.setPosition(this.id);

        if (board.getBank() <= luckPoint) {
            player.increaseMoney(board.getBank());
            board.decreaseBank(board.getBank());
        } else {
            player.increaseMoney(luckPoint);
            board.decreaseBank(luckPoint);
        }
    }
}
