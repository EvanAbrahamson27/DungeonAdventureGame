package model;

import java.util.*;

public class DungeonMap {
    private final Room[][] myMap;
    private final int SMALL_WIDTH = 5;
    private final int SMALL_HEIGHT = 5;
    private final int BIG_WIDTH = SMALL_WIDTH * 2 + 1;
    private final int BIG_HEIGHT = SMALL_HEIGHT * 2 + 1;

    private final Map<Room, List<Room>> adjacenyList;
    private Hero myHero;

    private int SKELETONS = 5;
    private int POTIONS = 2;

    public DungeonMap(String characterSelect) {

        if (characterSelect.equalsIgnoreCase("Warrior")) {
            this.myHero = new Warrior("Test_Hero", 100, 10, 30, 60, 85,1, 0);
        } else if (characterSelect.equalsIgnoreCase("Priestess")) {
            this.myHero = new Priestess("Test_Hero", 100, 10, 30, 60, 85,1, 0);
        } else if (characterSelect.equalsIgnoreCase("Thief")) {
            this.myHero = new Thief("Test_Hero", 100, 10, 30, 60, 85,1, 0);
        } else {
            this.myHero = null;
        }

        this.adjacenyList = new HashMap<>();

        // 10 x 10 2d array of rooms
        Room[][] tempMap = new Room[SMALL_WIDTH][SMALL_HEIGHT];

        for (int i = 0; i < SMALL_WIDTH; i++) {
            for (int j = 0; j < SMALL_HEIGHT; j++) {
                tempMap[i][j] = new Room(null, null, null, i, j, false);
                this.adjacenyList.put(tempMap[i][j], new ArrayList<>());
            }
        }

        createAdjacencyList(tempMap);

        // Generate a maze within the map from the start (0,0) to the end (WIDTH - 1, HEIGHT - 1)
        this.myMap = generateMaze(tempMap);

        // Generate characters (hero, monsters, and potions) on the map
        generateCharacters();
    }

    private void createAdjacencyList(Room[][] tempMap) {

        // The for loop below accesses each node in the Node maze
        for (int i = 0; i < this.SMALL_WIDTH; i++) {
            for (int j = 0; j < this.SMALL_HEIGHT; j++) {

                // Inserts our node into our adjacency list map and initializes an empty arraylist
                // to store the left, top, right, and bottom neighbors.
                this.adjacenyList.put(tempMap[i][j], new ArrayList<>());

                // Left
                if ((j - 1) >= 0 && tempMap[i][j - 1] != null) {
                    Room left = tempMap[i][j - 1];
                    this.adjacenyList.get(tempMap[i][j]).add(left); // Add neighbor to adjacency list
                    tempMap[i][j].leftNeighbor(left);
                }

                // Right
                if ((j + 1) < tempMap[0].length && tempMap[i][j + 1] != null) {
                    Room right = tempMap[i][j + 1];
                    this.adjacenyList.get(tempMap[i][j]).add(right); // Add neighbor to adjacency list
                    tempMap[i][j].rightNeighbor(right);
                }

                // Bottom
                if ((i + 1) < tempMap.length && tempMap[i + 1][j] != null) {
                    Room bottom = tempMap[i + 1][j];
                    this.adjacenyList.get(tempMap[i][j]).add(bottom); // Add neighbor to adjacency list
                    tempMap[i][j].bottomNeighbor(bottom);
                }

                // Top
                if ((i - 1) >= 0 && tempMap[i - 1][j] != null) {
                    Room top = tempMap[i - 1][j];
                    this.adjacenyList.get(tempMap[i][j]).add(top); // Add neighbor to adjacency list
                    tempMap[i][j].topNeighbor(top);
                }
            }
        }
    }

    private Room[][] generateMaze(Room[][] tempMap) {

        // Construct maze with walls
        Room[][] mazeMap = new Room[tempMap.length * 2 + 1][tempMap[0].length * 2 + 1];

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

        Room startingRoom = tempMap[0][0];

        if (startingRoom != null) {

            r.push(startingRoom);
            startingRoom.setVisited(true);

            // Remove the wall to indicate the room has been visited
            mazeMap[startingRoom.getXLocation() * 2 + 1][startingRoom.getYLocation() * 2 + 1].setIsWall(false);

            while(r.size() > 0) {
                Room currRoom = r.pop();

                List<Room> neighbors = this.adjacenyList.get(currRoom);

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

    private void generateCharacters() {

        // Generate monsters on the map randomly
        generateMonsters();

        // Generate potions on the map randomly
        generatePotions();
    }

    public Hero getHero() {
        return this.myHero;
    }

    private void generateMonsters() {
        for (int i = 0; i < this.SKELETONS; i++) {
            int x = (int) (Math.random() * (BIG_WIDTH));
            int y = (int) (Math.random() * (BIG_HEIGHT));
            if (this.myMap[x][y].isEmptySpace() == true && !(x == 1 && y == 0) && this.myMap[x][y].isWall() == false) {
                this.myMap[x][y] = new Room(new Monster("Skeleton", 50, 5, 15, 30, 95, 50, x, y), null, null, x, y, false);
            } else {
                i--;
            }
        }
    }

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

    public Room[][] getMap() {
        updateMapStatus();
        return this.myMap;
    }

    public Room getRoomAtLocation(final int theXLocation, final int theYLocation) {
        if (theXLocation < 0 || theXLocation >= BIG_WIDTH || theYLocation < 0 || theYLocation >= BIG_HEIGHT) {
            return null;
        }
        return this.myMap[theXLocation][theYLocation];
    }

    public void updateMapStatus() {
        for (int i = 0; i < BIG_WIDTH; i++) {
            for (int j = 0; j < BIG_HEIGHT; j++) {
                if (this.myMap[i][j].getMonster() != null && this.myMap[i][j].getMonster().getIsDead()) {
                    this.myMap[i][j] = new Room(null, null, null, i, j, false);
                }
            }
        }
    }
}
