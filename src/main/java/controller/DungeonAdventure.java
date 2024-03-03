package controller;

import model.*;
import view.GameWindow;

public class DungeonAdventure {
    public static String[] myArgs;
    public static StringBuilder myLog;
    public static Monster myMonster;
    private static boolean myFirstLaunch = true;
    private static String myName;

    public static void main(final String[] theArgs) {
        myArgs = theArgs;
        setupGame();
    }

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

    public static String getLog() {
        return myLog.toString();
    }
    public static void addToLog(final String theMessage) {
        myLog.append(theMessage).append("\n");
    }
}
