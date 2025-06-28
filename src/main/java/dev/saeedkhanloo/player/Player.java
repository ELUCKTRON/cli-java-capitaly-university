package dev.saeedkhanloo.player;

import java.util.ArrayList;

import dev.saeedkhanloo.board.CyclicalBoard;
import dev.saeedkhanloo.board.field.House;
import dev.saeedkhanloo.board.field.Property;

/**
 * Abstract class representing a player in the game.
 * Each player has a strategy for buying properties and can go bankrupt.
 */
public abstract class Player {
    /** The player's name. */
    protected String name;
    /** The player's current money. */
    protected int money;
    /** The player's current position on the board. */
    protected int position;
    /** The properties owned by the player. */
    protected ArrayList<Property> properties;
    /** Whether the player is out of the game. */
    protected boolean isGameOver;

    /**
     * Constructs a player with the given name.
     * @param name The player's name
     */
    public Player(String name) {
        this.name = name;
        this.money = 10000;
        this.position = 0;
        properties = new ArrayList<>();
        isGameOver = false;
    }

    /**
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the player's current money
     */
    public int getMoney() {
        return money;
    }

    /**
     * Increases the player's money by the specified amount.
     * @param money Amount to add
     */
    public void increaseMoney(int money) {
        this.money += money;
    }

    /**
     * Decreases the player's money by the specified amount.
     * @param money Amount to subtract
     */
    public void decreaseMoney(int money) {
        this.money -= money;
    }

    /**
     * @return the player's current position on the board
     */
    public int getPosition() {
        return position;
    }

    /**
     * Sets the player's position on the board.
     * @param position The new position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Updates the player's property list when an empty land is upgraded to a house.
     * @param emptyLand The empty land property
     * @param house     The new house property
     */
    public void updateProperty(Property emptyLand, Property house) {
        this.properties.remove(emptyLand);
        this.properties.add(house);
    }

    /**
     * @return true if the player is out of the game, false otherwise
     */
    public boolean isGameOver() {
        return isGameOver;
    }

    /**
     * Abstract strategy method to be implemented by subclasses.
     * @param board    The game board
     * @param property The property to consider buying
     */
    public abstract void strategy(CyclicalBoard board, Property property);

    /**
     * Attempts to buy a property if the investment is sufficient.
     * @param board   The game board
     * @param property The property to buy
     * @param invest   The amount the player is willing to invest
     */
    protected void buy(CyclicalBoard board, Property property, int invest) {
        if (invest >= Property.BUY_PRICE) {
            this.decreaseMoney(Property.BUY_PRICE);
            board.increaseBank(Property.BUY_PRICE);
            properties.add(property);
            property.setOwner(this);
        }
    }

    /**
     * Handles the player going bankrupt: transfers all money to the bank, clears properties, and sets isGameOver.
     * @param board The game board
     */
    public void gameOver(CyclicalBoard board) {
        this.isGameOver = true;
        board.increaseBank(this.money);
        this.decreaseMoney(this.money);

        for (Property p : properties) {
            p.setOwner(null);
        }

        this.properties = new ArrayList<>();
    }

    /**
     * Calculates the player's worth based on money and properties owned.
     * Houses are worth buy price + build price; empty land is worth buy price.
     * @return The player's total worth
     */
    public int worth() {
        int sum = 0;
        if (!this.isGameOver) {
            sum = this.money;
            for (Property p : properties) {
                if (p instanceof House) {
                    sum += Property.BUY_PRICE + Property.BUILD_PRICE;
                } else {
                    sum += Property.BUY_PRICE;
                }
            }
        }
        return sum;
    }

    /**
     * Returns a string representation of the player, including properties if any.
     * @return String representation of the player
     */
    @Override
    public String toString() {
        String s = this.name + " isGameOver : " + this.isGameOver + " cash : " + this.money + " worth : " + this.worth()
                + " Number of property : " + this.properties.size();

        if (properties.size() > 0) {
            String v = " Properties : ";
            for (Property p : properties) {
                v += p.getClass().getSimpleName() + " at : " + p.getId() + " ";
            }
            return s + v;
        }
        return s;
    }
}
