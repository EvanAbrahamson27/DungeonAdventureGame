package controller;

import model.*;
import view.GameWindow;
import view.SavedState;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DungeonAdventure {
    public static String[] myArgs;
    public static StringBuilder myLog;
    public static Monster myMonster;
    private static boolean myFirstLaunch = true;
    private static String myName;

    private static GameWindow myGameWindow;

    public static void main(final String[] theArgs) {
        myArgs = theArgs;
        setupGame();
    }

    public static void setupGame() {
        myName = "Adventurer";
        myLog = new StringBuilder();
        myGameWindow = new GameWindow();

        if (myFirstLaunch) {
            myFirstLaunch = false;
            myGameWindow.main(myArgs);
        } else {
            myMonster = null;

            // Restart game
            myGameWindow.restartWindow();
        }
    }

    public static String getLog() {
        return myLog.toString();
    }
    public static void addToLog(final String theMessage) {
        myLog.append(theMessage).append("\n");
    }
}
