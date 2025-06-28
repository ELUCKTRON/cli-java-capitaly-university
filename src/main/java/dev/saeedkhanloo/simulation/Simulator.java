package dev.saeedkhanloo.simulation;

import dev.saeedkhanloo.player.Player;

import java.util.ArrayList;

/**
 * Handles simulation setup and execution for the Capitaly University game.
 * Loads the game from a file and runs the simulation.
 */
public class Simulator {

    /**
     * The LoadingGame instance used for simulation.
     */
    private LoadingGame loadingGame;

    /**
     * @return the LoadingGame instance
     */
    public LoadingGame getLoadingGame() {
        return loadingGame;
    }

    /**
     * Constructs a Simulator with the given input file address.
     * @param fileAddress The path to the input file
     */
    public Simulator(String fileAddress) {
        loadingGame = new LoadingGame(fileAddress);
    }

    /**
     * Runs the simulation based on the loaded game and dice rolls.
     * No parameters or return value.
     */
    public void simulation() {
        if (loadingGame.isOk) {
            loadingGame.loading();
            for (int i = 0; i < loadingGame.getDices().size(); i++) {
                int playerTurn = i % loadingGame.getBoard().getPlayers().size();
                int dice = loadingGame.getDices().get(i);
                loadingGame.getBoard().play(loadingGame.getBoard().getPlayers().get(playerTurn), dice);
            }
            System.out.println("                                 -END OF SIMULATION -");
            ArrayList<Player> result = loadingGame.result();
            for (Player x : result) {
                System.out.println(x);
            }
            System.out.println();
            System.out.println("Winner is : " + result.getFirst());
        }
    }
}
