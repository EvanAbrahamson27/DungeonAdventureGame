/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */
package controller;

import model.*;
import view.GameWindow;
/**
 * The DungeonAdventure class serves as the controller a simple dungeon crawler game.
 * It manages the game setup, including initialization of the game window, controlling the game flow, and logging game events.
 */
public class DungeonAdventure {

    /**
     * Command line arguments passed to the program.
     */
    public static String[] myArgs;
    /**
     * Log of all game events.
     */
    public static StringBuilder myLog;
    /**
     * The monster encountered in the dungeon.
     */
    public static Monster myMonster;
    /**
     * Flag to check if the game is launched for the first time.
     */
    private static boolean myFirstLaunch = true;

    /**
     * Name of the character.
     */
    private static String myName;

    /**
     * The main method to start the dungeon adventure game.
     * @param theArgs Command line arguments passed to the program.
     */
    public static void main(final String[] theArgs) {
        myArgs = theArgs;
        setupGame();
    }

    /**
     * Sets up the game environment, initializes variables, and starts the game window.
     * It is responsible for handling the first game launch and subsequent restarts.
     */
    public static void setupGame() {
        myName = "Adventurer";
        myLog = new StringBuilder();
        GameWindow game = new GameWindow();

        if (myFirstLaunch) {
            myFirstLaunch = false;
            game.main(myArgs);
        } else {
            myMonster = null;

            // Restart game
            game.restartWindow();
        }
    }

    /**
     * Retrieves the log of all game events.
     * @return A string representation of the game event log.
     */
    public static String getLog() {
        return myLog.toString();
    }
    /**
     * Adds a message to the game event log.
     * @param theMessage The message to be added to the log.
     */
    public static void addToLog(final String theMessage) {
        myLog.append(theMessage).append("\n");
    }
}
