package controller;

import model.*;
import view.CharacterWindow;
import view.GameWindow;

public class DungeonAdventure {
    public static String[] myArgs;
    public static StringBuilder myLog;
    public static Monster myMonster;
    private static boolean myFirstLaunch = true;
    private static String myName;

    public static void main(String[] theArgs) {
        myArgs = theArgs;
        setupGame();
    }

    public static void setupGame() {
        myName = "Adventurer";
        myLog = new StringBuilder();
        if (myFirstLaunch) {
            myFirstLaunch = false;
            GameWindow.main(myArgs);
        } else {
            myMonster = null;
            new CharacterWindow();
        }
    }

    public static String getLog() {
        return myLog.toString();
    }
    public static void addToLog(String theMessage) {
        myLog.append(theMessage).append("\n");
    }
}
