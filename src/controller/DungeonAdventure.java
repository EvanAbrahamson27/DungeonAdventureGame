package controller;

import model.Hero;
import model.Item;
import view.GameWindow;

import java.io.IOException;

public class DungeonAdventure {
    public static StringBuilder myLog;

    public static void main(String[] theArgs) throws IOException {
        setupGame();
    }

    private static void setupGame() throws IOException {
        myLog = new StringBuilder();
        Hero hero = new Hero("Test", 100, 10,
                30, 60, 85);
        GameWindow gameWindow = new GameWindow(hero);
        hero.addToInventory(new Item('h', 25,
                "Healing Potion")); //Temp for Item Tests
    }

    public static String getLog() {
        return myLog.toString();
    }
    public static void addToLog(String theMessage) {
        myLog.append(theMessage).append("\n");
    }
}
