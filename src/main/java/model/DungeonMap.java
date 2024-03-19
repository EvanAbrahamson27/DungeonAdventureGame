/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */
package model;

import java.io.Serializable;
import java.util.*;
/**
 * Represents the dungeon map for the game.
 * The map consists of rooms arranged in a grid, containing heroes, monsters, and items.
 * It supports generating and managing the layout of rooms, the characters (heroes and monsters),
 * and items like potions and pillars of OO within the game.
 */
public class DungeonMap implements Serializable {
    private static final long serialVers = 1L;
    private final Room[][] myMap;
    private final int SMALL_WIDTH = 5;
    private final int SMALL_HEIGHT = 5;
    private final int BIG_WIDTH = SMALL_WIDTH * 2 + 1;
    private final int BIG_HEIGHT = SMALL_HEIGHT * 2 + 1;

    private final Map<Room, List<Room>> myAdjacencyList;
    private final Hero myHero;

    private final int SKELETONS = 3;
    private final int OGRES = 2;
    private final int GREMLINS = 2;

    private final int POTIONS = 2;

    /**
     * Constructs the DungeonMap, initializes map dimensions, and populates the map with rooms,
     * a hero based on selected character, monsters, and items.
     * @param theCharacterSelect The type of hero character selected by the player (Warrior, Priestess, Thief).
     * @param theName The name given to the hero character.
     */
    public DungeonMap(final String theCharacterSelect, final String theName) {

        if (theCharacterSelect.equalsIgnoreCase("Warrior")) {
            this.myHero = new Warrior(theName, 125, 35, 60, 4, 80, 20,1, 0);
        } else if (theCharacterSelect.equalsIgnoreCase("Priestess")) {
            this.myHero = new Priestess(theName, 75, 25, 45, 5, 70, 30,1, 0);
        } else if (theCharacterSelect.equalsIgnoreCase("Thief")) {
            this.myHero = new Thief(theName, 75, 20, 40, 6, 80, 40,1, 0);
        } else {
            this.myHero = null;
        }

        this.myAdjacencyList = new HashMap<>();

        // 10 x 10 2d array of rooms
        Room[][] tempMap = new Room[SMALL_WIDTH][SMALL_HEIGHT];

        for (int i = 0; i < SMALL_WIDTH; i++) {
            for (int j = 0; j < SMALL_HEIGHT; j++) {
                tempMap[i][j] = new Room(null, null, null, i, j, false);
                this.myAdjacencyList.put(tempMap[i][j], new ArrayList<>());
            }
        }

        createAdjacencyList(tempMap);

        // Generate a maze within the map from the start (0,0) to the end (WIDTH - 1, HEIGHT - 1)
        this.myMap = generateMaze(tempMap);

        // Generate characters (hero, monsters, and potions) on the map
        generateCharacters();
    }

    /**
     * Creates adjacency lists for rooms to facilitate maze generation and movement within the map.
     * @param theTempMap A temporary map structure used to construct the dungeon layout.
     */
    private void createAdjacencyList(final Room[][] theTempMap) {

        // The for loop below accesses each node in the Node maze
        for (int i = 0; i < this.SMALL_WIDTH; i++) {
            for (int j = 0; j < this.SMALL_HEIGHT; j++) {

                // Inserts our node into our adjacency list map and initializes an empty arraylist
                // to store the left, top, right, and bottom neighbors.
                this.myAdjacencyList.put(theTempMap[i][j], new ArrayList<>());

                // Left
                if ((j - 1) >= 0 && theTempMap[i][j - 1] != null) {
                    Room left = theTempMap[i][j - 1];
                    this.myAdjacencyList.get(theTempMap[i][j]).add(left); // Add neighbor to adjacency list
                    theTempMap[i][j].leftNeighbor(left);
                }

                // Right
                if ((j + 1) < theTempMap[0].length && theTempMap[i][j + 1] != null) {
                    Room right = theTempMap[i][j + 1];
                    this.myAdjacencyList.get(theTempMap[i][j]).add(right); // Add neighbor to adjacency list
                    theTempMap[i][j].rightNeighbor(right);
                }

                // Bottom
                if ((i + 1) < theTempMap.length && theTempMap[i + 1][j] != null) {
                    Room bottom = theTempMap[i + 1][j];
                    this.myAdjacencyList.get(theTempMap[i][j]).add(bottom); // Add neighbor to adjacency list
                    theTempMap[i][j].bottomNeighbor(bottom);
                }

                // Top
                if ((i - 1) >= 0 && theTempMap[i - 1][j] != null) {
                    Room top = theTempMap[i - 1][j];
                    this.myAdjacencyList.get(theTempMap[i][j]).add(top); // Add neighbor to adjacency list
                    theTempMap[i][j].topNeighbor(top);
                }
            }
        }
    }

    /**
     * Generates a maze within the map, carving out paths between rooms and setting wall boundaries.
     * @param theTempMap A temporary map structure used for initial room setup.
     * @return A two-dimensional array of Room objects representing the completed maze.
     */
    private Room[][] generateMaze(final Room[][] theTempMap) {

        // Construct maze with walls
        Room[][] mazeMap = new Room[theTempMap.length * 2 + 1][theTempMap[0].length * 2 + 1];

        for (int i = 0; i < mazeMap.length; i++) {
            for (int j = 0; j < mazeMap[i].length; j++) {
                if (i % 2 != 0 && j % 2 != 0) {
                    mazeMap[i][j] = new Room(null, null, null, i, j, false);
                } else {
                    mazeMap[i][j] = new Room(null, null, null, i, j, true);
                }
            }
        }

        // Remove the wall at the starting position
        mazeMap[1][0].setIsWall(false);

        // A stack to keep track of visited rooms
        Stack<Room> r = new Stack<>();

        Room startingRoom = theTempMap[0][0];

        if (startingRoom != null) {

            r.push(startingRoom);
            startingRoom.setVisited(true);

            // Remove the wall to indicate the room has been visited
            mazeMap[startingRoom.getXLocation() * 2 + 1][startingRoom.getYLocation() * 2 + 1].setIsWall(false);

            while(r.size() > 0) {
                Room currRoom = r.pop();

                List<Room> neighbors = this.myAdjacencyList.get(currRoom);

                // Shuffle neighbors to create a random traversal of the maze
                Collections.shuffle(neighbors);

                for (Room neighbor : neighbors) {

                    if (neighbor.getIsVisited() == false) {
                        neighbor.setVisited(true);

                        // Remove the wall to indicate the room has been visited
                        mazeMap[neighbor.getXLocation() * 2 + 1][neighbor.getYLocation() * 2 + 1].setIsWall(false);

                        // Break down the wall of the left neighbor
                        if (currRoom.getLeftNeighbor() == neighbor) {
                            mazeMap[currRoom.getXLocation() * 2 + 1][currRoom.getYLocation() * 2].setIsWall(false);
                            currRoom.getLeftNeighbor().setIsVisited(true);
                        }

                        // Break down the wall of the right neighbor
                        if (currRoom.getRightNeighbor() == neighbor) {
                            mazeMap[currRoom.getXLocation() * 2 + 1][currRoom.getYLocation() * 2 + 1 + 1].setIsWall(false);
                            currRoom.getRightNeighbor().setIsVisited(true);
                        }

                        // Break down the wall of the top neighbor
                        if (currRoom.getTopNeighbor() == neighbor) {
                            mazeMap[currRoom.getXLocation() * 2][currRoom.getYLocation() * 2 + 1].setIsWall(false);
                            currRoom.getTopNeighbor().setIsVisited(true);
                        }

                        // Break down the wall of the bottom neighbor
                        if (currRoom.getBottomNeighbor() == neighbor) {
                            mazeMap[currRoom.getXLocation() * 2 + 1 + 1][currRoom.getYLocation() * 2 + 1].setIsWall(false);
                            currRoom.getBottomNeighbor().setIsVisited(true);
                        }

                        // Add neighbor to stack to visit its neighbors
                        r.push(neighbor);
                    }
                }
            }
        }

        // Add treasure in the bottom right corner of the maze
        mazeMap[mazeMap.length - 2][mazeMap[0].length - 1] = new Room(null, null, new Item('h', 10, "Healing Potion"), mazeMap.length - 2, mazeMap[0].length - 1, false);

        return mazeMap;
    }

    /**
     * Populates the dungeon map with monsters, potions, and pillars based on predefined counts.
     */
    private void generateCharacters() {

        // Generate monsters on the map randomly
        generateMonsters();

        // Generate potions on the map randomly
        generatePotions();

        // Generate pillars on the map randomly
        generatePillars();
    }

//    private void generateMonsters() {
//        for (int i = 0; i < this.SKELETONS; i++) {
//            int x = (int) (Math.random() * (BIG_WIDTH));
//            int y = (int) (Math.random() * (BIG_HEIGHT));
//            if (this.myMap[x][y].isEmptySpace() == true && !(x == 1 && y == 0) && this.myMap[x][y].isWall() == false) {
//                this.myMap[x][y] = new Room(new Monster("Skeleton", 60, 5, 15, 3, 95, 50, x, y), null, null, x, y, false);
//            } else {
//                i--;
//            }
//        }
//    }

    /**
     * Randomly places monsters within the dungeon map based on predefined types and counts.
     */
    private void generateMonsters() {
        int totalMonsters = this.SKELETONS + this.OGRES + this.GREMLINS;

        for (int i = 0; i < totalMonsters; i++) {
            int x = (int) (Math.random() * BIG_WIDTH);
            int y = (int) (Math.random() * BIG_HEIGHT);

            if (this.myMap[x][y].isEmptySpace() && !(x == 1 && y == 0) && !this.myMap[x][y].isWall()) {
                Monster monster = null;
                int monsterType = (int) (Math.random() * 3);
                switch (monsterType) {
                    case 0 -> // Create a Skeleton
                            monster = new Skeleton("Skeleton", 70, 15, 30, 5,
                                    0.8, 0.4, 20, 40, x, y);
                    case 1 -> // Create an Ogre
                            monster = new Ogre("Ogre", 70, 15, 30, 5,
                                    0.8, 0.4, 20, 40, x, y);
                    case 2 -> // Create a Gremlin
                            monster = new Gremlin("Gremlin", 70, 15, 30, 5,
                                    0.8, 0.4, 20, 40, x, y);
                }

                if (monster != null) {
                    this.myMap[x][y] = new Room(monster, null, null, x, y, false);
                } else {
                    i--;
                }
            } else {
                i--;
            }
        }
    }


    /**
     * Randomly places healing potions within the dungeon map based on a predefined count.
     */
    private void generatePotions() {
        for (int i = 0; i < this.POTIONS; i++) {
            int x = (int) (Math.random() * BIG_WIDTH);
            int y = (int) (Math.random() * BIG_HEIGHT);
            if (this.myMap[x][y].isEmptySpace() == true && !(x == 1 && y == 0) && this.myMap[x][y].isWall() == false) {
                this.myMap[x][y] = new Room(null, null, new Item('h', 10, "Healing Potion"), x, y, false);
            } else {
                i--;
            }
        }
    }

    /**
     * Randomly places pillars of OO within the dungeon map, ensuring all four are placed.
     */
    private void generatePillars() {
        for (int i = 0; i < 4; i++) {
            int x = (int) (Math.random() * BIG_WIDTH);
            int y = (int) (Math.random() * BIG_HEIGHT);
            if (this.myMap[x][y].isEmptySpace() == true && !(x == 1 && y == 0) && this.myMap[x][y].isWall() == false) {
                this.myMap[x][y] = new Room(null, null, new Item('p', 0, "Pillar"), x, y, false);
            } else {
                i--;
            }
        }
    }

    /**
     * Retrieves the hero character associated with this dungeon map.
     * @return The hero present in the dungeon.
     */
    public Hero getHero() {
        return this.myHero;
    }

    /**
     * Gets the current state of the dungeon map as a two-dimensional array of rooms.
     * Also updates the map status by removing dead monsters from the rooms.
     * @return A two-dimensional array representing the current state of the dungeon.
     */
    public Room[][] getMap() {
        updateMapStatus();
        return this.myMap;
    }

    /**
     * Retrieves the room located at the specified coordinates in the dungeon.
     * @param theXLocation The x-coordinate of the room.
     * @param theYLocation The y-coordinate of the room.
     * @return The room at the specified coordinates, or null if coordinates are out of bounds.
     */
    public Room getRoomAtLocation(final int theXLocation, final int theYLocation) {
        if (theXLocation < 0 || theXLocation >= BIG_WIDTH || theYLocation < 0 || theYLocation >= BIG_HEIGHT) {
            return null;
        }
        return this.myMap[theXLocation][theYLocation];
    }

    /**
     * Updates the status of the map, typically used to clean up after monsters have been defeated.
     */
    public void updateMapStatus() {
        for (int i = 0; i < BIG_WIDTH; i++) {
            for (int j = 0; j < BIG_HEIGHT; j++) {
                if (this.myMap[i][j].getMonster() != null && this.myMap[i][j].getMonster().getIsDead()) {
                    this.myMap[i][j] = new Room(null, null, null, i, j, false);
                }
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BIG_WIDTH; i++) {
            for (int j = 0; j < BIG_HEIGHT; j++) {
                sb.append(this.myMap[i][j].toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
