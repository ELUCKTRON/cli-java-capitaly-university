package dev.saeedkhanloo.board;

import java.util.ArrayList;

import dev.saeedkhanloo.board.field.Field;
import dev.saeedkhanloo.board.field.Property;
import dev.saeedkhanloo.player.Player;

/**
 * Represents the cyclical board for the Capitaly University game.
 * Handles fields, bank, turns, players, and game logic.
 *
 * @author SAEED FATHALLAH KHANLOOBRISE-F0HAXC
 */
public class CyclicalBoard {

    /**
     * List of fields on the board that players can land on.
     */
    private final ArrayList<Field> fields;

    /**
     * The bank holds the game's money. Players gain or lose money from/to the bank.
     */
    private int bank;

    /**
     * Tracks the number of turns passed.
     */
    private int turn;

    /**
     * List of players currently playing on the board.
     */
    private final ArrayList<Player> players;

    /**
     * Constructs a CyclicalBoard with the given fields, players, and starting bank value.
     * Throws IllegalArgumentException if bank is negative or fields/players are empty.
     *
     * @param fields  List of fields on the board
     * @param players List of players
     * @param bank    Starting bank value
     */
    public CyclicalBoard(ArrayList<Field> fields, ArrayList<Player> players, int bank) {
        this.fields = fields;
        this.players = players;

        if (bank < 0 || fields.isEmpty() || players.isEmpty()) {
            throw new IllegalArgumentException("Bank can't be negative and fields/players can't be empty");
        }
        this.bank = bank;
        turn = 0;
    }

    /**
     * @return the list of players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * @return the current bank value
     */
    public int getBank() {
        return bank;
    }

    /**
     * Increases the bank by the specified amount.
     * @param money Amount to add
     */
    public void increaseBank(int money) {
        this.bank += money;
    }

    /**
     * Decreases the bank by the specified amount.
     * @param money Amount to subtract
     */
    public void decreaseBank(int money) {
        this.bank -= money;
    }

    /**
     * Updates the field at the given property ID to a new property (e.g., when a house is built).
     * @param house The new property to set
     */
    public void building(Property house) {
        this.fields.set(house.getId(), house);
    }

    /**
     * Logs the current state after a player's move.
     * @param player      The player who moved
     * @param dice        The dice roll
     * @param newPosition The new position of the player
     */
    public void log(Player player, int dice, int newPosition) {
        System.out.println("Player : " + player.getName() + ", on turn : " + turn
                + ", dice : " + dice + ", move to new position : "
                + newPosition + ", type of this position : " +
                this.fields.get(newPosition).getClass().getSimpleName()
                + ", current money : " + player.getMoney());
    }

    /**
     * Moves the player based on the dice roll and performs the field's action.
     * @param player The player to move
     * @param dice   The dice roll
     */
    public void play(Player player, int dice) {
        if (!player.isGameOver()) {
            int newPosition = (player.getPosition() + dice) % this.fields.size();
            fields.get(newPosition).action(this, player);
            log(player, dice, newPosition);
        }
        this.turn++;
    }

    /**
     * Returns a leaderboard of players sorted by their worth (descending).
     * @return Sorted list of players
     */
    public ArrayList<Player> leaderBoard() {
        ArrayList<Player> leaderboard = new ArrayList<>(players);
        leaderboard.sort((x, y) -> Integer.compare(y.worth(), x.worth()));
        return leaderboard;
    }

}
