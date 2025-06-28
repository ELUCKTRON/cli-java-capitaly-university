package dev.saeedkhanloo.board.field;

import dev.saeedkhanloo.board.CyclicalBoard;
import dev.saeedkhanloo.player.Player;

/**
 * Represents a service field on the board. When a player lands here, they must pay a fee to the bank.
 * If the player can't pay, they go bankrupt and the bank takes all their remaining money.
 */
public class Service extends Field {

    /**
     * The fee to be paid when landing on this field.
     */
    private final int serviceFee;

    /**
     * Constructs a Service field with the given ID and service fee.
     * @param id         The position of the field on the board
     * @param serviceFee The fee to be paid
     */
    public Service(int id, int serviceFee) {
        super(id);
        this.serviceFee = serviceFee;
    }

    /**
     * Handles the action when a player lands on this field.
     * @param board  The game board
     * @param player The player taking the action
     */
    @Override
    public void action(CyclicalBoard board, Player player) {
        player.setPosition(this.id);

        if (player.getMoney() < serviceFee) {
            player.gameOver(board);
        } else {
            player.decreaseMoney(serviceFee);
            board.increaseBank(serviceFee);
        }
    }
}
