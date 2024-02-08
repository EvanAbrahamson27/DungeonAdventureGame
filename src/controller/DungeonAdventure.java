package controller;

import model.Hero;
import model.Item;
import view.GameWindow;

public class DungeonAdventure {
    public static StringBuilder myLog;
    public static Hero myHero;

    public static void main(String[] theArgs) {
        setupGame(theArgs);
    }

    private static void setupGame(String[] theArgs) {
        myLog = new StringBuilder();
        myHero = new Hero("Test_Hero", 100, 10,
                30, 60, 85);
        myHero.addToInventory(new Item('h', 25,
                "Healing Potion")); //Temp for Item Tests
        GameWindow.main(theArgs);
    }

    public static String getLog() {
        return myLog.toString();
    }
    public static void addToLog(String theMessage) {
        myLog.append(theMessage).append("\n");
    }
}
