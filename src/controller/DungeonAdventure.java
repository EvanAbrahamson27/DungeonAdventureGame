package controller;

import model.*;
import view.GameWindow;

public class DungeonAdventure {
    public static StringBuilder myLog;
    public static Hero myHero;
    public static Monster myMonster;
    public static DungeonMap myDungeonMap;

    public static void main(String[] theArgs) {
        setupGame(theArgs);
    }

    private static void setupGame(String[] theArgs) {
        myLog = new StringBuilder();
        myDungeonMap = new DungeonMap();
        myHero = new Thief("Test_Priestess");
        GameWindow.main(theArgs);
    }

    public static String getLog() {
        return myLog.toString();
    }
    public static void addToLog(String theMessage) {
        myLog.append(theMessage).append("\n");
    }
}
