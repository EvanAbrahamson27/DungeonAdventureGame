package controller;

import model.*;
import view.CharacterWindow;
import view.GameWindow;

public class DungeonAdventure {
    public static String[] myArgs;
    public static StringBuilder myLog;
    public static Hero myHero;
    public static Monster myMonster;
    public static DungeonMap myDungeonMap;
    private static boolean myFirstLaunch = true;
    private static String myName;

    public static void main(String[] theArgs) {
        myArgs = theArgs;
        setupGame();
    }

    public static void setupGame() {
        myName = "Adventurer";
        myLog = new StringBuilder();
        myDungeonMap = new DungeonMap();

        myHero = myDungeonMap.getHero();
        myHero.setRoom(myDungeonMap.getRoomAtLocation(myHero.getX(), myHero.getY()));
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

    public static void setClass(final String theClass) {
        switch (theClass) {
            case "Priestess" : {
                DungeonAdventure.myHero = new Priestess(myName);
            } case "Warrior" : {
                DungeonAdventure.myHero = new Warrior(myName);
            } case "Thief" : {
                DungeonAdventure.myHero = new Thief(myName);
            }
        }
    }
}
