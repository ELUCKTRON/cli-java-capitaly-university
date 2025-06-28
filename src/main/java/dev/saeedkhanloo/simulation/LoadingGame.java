package dev.saeedkhanloo.simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import dev.saeedkhanloo.board.CyclicalBoard;
import dev.saeedkhanloo.board.field.EmptyLand;
import dev.saeedkhanloo.board.field.Field;
import dev.saeedkhanloo.board.field.Lucky;
import dev.saeedkhanloo.board.field.Service;
import dev.saeedkhanloo.player.Careful;
import dev.saeedkhanloo.player.Greedy;
import dev.saeedkhanloo.player.Player;
import dev.saeedkhanloo.player.Tactical;

/**
 * Handles loading the game configuration from a file and initializing the game state.
 */
public class LoadingGame {
    /** The starting amount of money in the bank. */
    private int startingBank;
    /** The game board instance. */
    private CyclicalBoard board;
    /** Scanner for reading the input file. */
    private Scanner sc;
    /** Indicates if the game was loaded successfully. */
    public boolean isOk = true;
    /** List of dice rolls for the simulation. */
    private ArrayList<Integer> dices = new ArrayList<>();

    /**
     * @return the list of dice rolls
     */
    public ArrayList<Integer> getDices() {
        return dices;
    }

    /**
     * @return the game board instance
     */
    public CyclicalBoard getBoard() {
        return board;
    }

    /**
     * Constructs a LoadingGame instance and checks if the file exists.
     * If the file does not exist, sets isOk to false.
     * @param filename The path to the input file
     */
    public LoadingGame(String filename) {
        try {
            File file = new File(filename);
            sc = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println("file " + filename + " can not be found");
            isOk = false;
        }
    }

    /**
     * Reads the game configuration from the file and initializes the board and players.
     * If there is a problem with the format, sets isOk to false and prints the expected format.
     */
    public void loading() {
        ArrayList<Field> fields = new ArrayList<>();
        ArrayList<Player> players = new ArrayList<>();
        if (sc.hasNext()) {
            try {
                String line = sc.nextLine();
                int length = Integer.parseInt(line.split(" ")[0]);
                startingBank = Integer.parseInt(line.split(" ")[1]);
                sc.nextLine();
                for (int i = 0; i < length; i++) {
                    Field field = null;
                    String[] type = sc.nextLine().split(" ");
                    field = switch (type[0]) {
                        case "Property" -> new EmptyLand(i);
                        case "Lucky" -> new Lucky(i, Integer.parseInt(type[1]));
                        case "Service" -> new Service(i, Integer.parseInt(type[1]));
                        default -> field;
                    };
                    if (field != null) {
                        fields.add(field);
                    }
                }
                sc.nextLine();
                int numPlayers = Integer.parseInt(sc.nextLine());
                for (int i = 0; i < numPlayers; i++) {
                    Player player = null;
                    String[] nameAndType = sc.nextLine().split(" ");
                    player = switch (nameAndType[1]) {
                        case "Greedy" -> new Greedy(nameAndType[0]);
                        case "Careful" -> new Careful(nameAndType[0]);
                        case "Tactical" -> new Tactical(nameAndType[0]);
                        default -> player;
                    };
                    if (player != null) {
                        players.add(player);
                    }
                }
                board = new CyclicalBoard(fields, players, startingBank);
                sc.nextLine();
                while (sc.hasNext()) {
                    String[] rolls = sc.nextLine().split(" ");
                    for (String roll : rolls) {
                        dices.add(Integer.parseInt(roll));
                    }
                }
                sc.close();
            } catch (Exception ex) {
                isOk = false;
                System.out.println(" data format inside the file is wrong\n" +
                        "it should be : \n" +
                        "numberOfFields BankMoney\n" +
                        "\n" +
                        "... fields (if Service or Lucky with amount of money)\n" +
                        "\n" +
                        "numberOfPlayers\n" +
                        "players Strategy\n" +
                        "\n" +
                        "dices of each player with space between" +
                        "...");
            }
        }
    }

    /**
     * @return ArrayList of all players who played this game, including bankrupt ones
     */
    public ArrayList<Player> result() {
        return board.leaderBoard();
    }
}
