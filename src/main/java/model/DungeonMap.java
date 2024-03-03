package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DungeonMap {
    private final Room[][] myMap;
    private final int WIDTH = 5;
    private final int HEIGHT = 5;

    private final Map<Room, List<Room>> adjacenyList;
    private final Hero myHero;

    private final int SKELETONS = 5;
    private final int POTIONS = 2;

    public DungeonMap(final String characterSelect) {

        if (characterSelect.equalsIgnoreCase("Warrior")) {
            this.myHero = new Warrior("Test_Hero", 100, 10, 30, 60, 85,0, 0);
        } else if (characterSelect.equalsIgnoreCase("Priestess")) {
            this.myHero = new Priestess("Test_Hero", 100, 10, 30, 60, 85,0, 0);
        } else if (characterSelect.equalsIgnoreCase("Thief")) {
            this.myHero = new Thief("Test_Hero", 100, 10, 30, 60, 85,0, 0);
        } else {
            this.myHero = null;
        }

        this.adjacenyList = new HashMap<>();

        // 10 x 10 2d array of rooms
        this.myMap = new Room[WIDTH][HEIGHT];

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                this.myMap[i][j] = new Room(null, null, null, i, j);
                this.adjacenyList.put(this.myMap[i][j], new ArrayList<>());
            }
        }

        // Generate a maze within the map from the start (0,0) to the end (WIDTH - 1, HEIGHT - 1)
        generateMaze();

        // Generate characters (hero, monsters, and potions) on the map
        generateCharacters();
    }

    private void generateMaze() {
        // Generate a maze within the map from the start (0,0) to the end (WIDTH - 1, HEIGHT - 1)


    }

    private void generateCharacters() {
        // Add player to start of the room
        this.myMap[0][0] = new Room(null, this.myHero, null, 0, 0);

        // Generate 4 monsters on the map randomly
        generateMonsters();

        // Generate 3 potions on the map randomly
        generatePotions();
    }

    public Hero getHero() {
        return this.myHero;
    }

    private void generateMonsters() {
        for (int i = 0; i < this.SKELETONS; i++) {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            if (this.myMap[x][y].isEmptySpace() == true && !(x == 0 && y == 0)) {
                this.myMap[x][y] = new Room(new Monster("Skeleton", 50, 5, 15, 30, 95, 50, x, y), null, null, x, y);
            } else {
                i--;
            }
        }
    }

    private void generatePotions() {
        for (int i = 0; i < this.POTIONS; i++) {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            if (this.myMap[x][y].isEmptySpace() == true && !(x == 0 && y == 0)) {
                this.myMap[x][y] = new Room(null, null, new Item('h', 10, "Healing Potion"), x, y);
            } else {
                i--;
            }
        }
    }

    public Room[][] getMap() {
        updateMapStatus();
        return this.myMap;
    }

    public Room getRoomAtLocation(final int theXLocation, final int theYLocation) {
        if (theXLocation < 0 || theXLocation >= WIDTH || theYLocation < 0 || theYLocation >= HEIGHT) {
            return null;
        }
        return this.myMap[theXLocation][theYLocation];
    }

    public void updateMapStatus() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (this.myMap[i][j].getMonster() != null && this.myMap[i][j].getMonster().getIsDead()) {
                    this.myMap[i][j] = new Room(null, null, null, i, j);
                }
            }
        }
    }
}
