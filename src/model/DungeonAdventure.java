package model;

import view.GameWindow;

import java.io.IOException;

public class DungeonAdventure {
    public static void main(String[] theArgs) throws IOException {
        Hero hero = new Hero("Test", 100, 10,
                30, 60, 85);
        GameWindow gameWindow = new GameWindow(hero);
    }
}
