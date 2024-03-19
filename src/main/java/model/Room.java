/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */
package model;

import controller.DungeonAdventure;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.CharacterWindow;
import java.io.Serializable;

/**
 * Represents a room in the dungeon map.
 */
public class Room implements Serializable {
    private Monster myMonster;
    private Hero myHero;
    private Item myItem;
    private final int myXLocation;
    private final int myYLocation;
    private boolean myIsVisited;
    private Room myTopNeighbor;
    private Room myBottomNeighbor;
    private Room myLeftNeighbor;
    private Room myRightNeighbor;
    private Boolean myIsWall;
    private boolean myIsVisible;

    /**
     * Constructs a room with the specified attributes.
     * @param theMonster The monster in the room.
     * @param theHero The hero in the room.
     * @param theItem The item in the room.
     * @param theXLocation The x-coordinate of the room.
     * @param theYLocation The y-coordinate of the room.
     * @param isWall True if the room is a wall, false otherwise.
     */
    public Room(final Monster theMonster, final Hero theHero, final Item theItem, final int theXLocation,
                final int theYLocation, final boolean isWall) {
        myMonster = theMonster;
        myItem = theItem;
        myXLocation = theXLocation;
        myYLocation = theYLocation;
        this.myIsVisited = false;
        this.myTopNeighbor = null;
        this.myBottomNeighbor = null;
        this.myLeftNeighbor = null;
        this.myRightNeighbor = null;
        this.myIsWall = isWall;
        this.myIsVisible = false;
    }

    /**
     * Returns the x-coordinate of the room.
     * @return The x-coordinate.
     */
    public int getXLocation() {
        return myXLocation;
    }

    /**
     * Returns the y-coordinate of the room.
     * @return The y-coordinate.
     */
    public int getYLocation() {
        return myYLocation;
    }

    /**
     * Handles encountering a monster in the room.
     * If the monster is not dead, starts a battle with the hero.
     */
    public void encounterMonster() {
        if (myMonster != null && !myMonster.getIsDead()) {
            DungeonAdventure.myMonster = myMonster;
            CharacterWindow.myHero.startBattle(DungeonAdventure.myMonster);
        }
    }

    /**
     * Handles encountering an item in the room.
     * Adds the item to the hero's inventory and removes it from the room.
     */
    public void encounterItem() {
        if (myItem != null) {
            CharacterWindow.myHero.addToInventory(myItem);
            myItem = null;
        }
    }

    /**
     * Returns the monster in the room.
     * @return The monster, or null if there is no monster.
     */
    public Monster getMonster() {
        if (myMonster != null) {
            return myMonster;
        } else {
            return null;
        }
    }

    /**
     * Returns the item in the room.
     * @return The item, or null if there is no item.
     */
    public Item getItem() {
        if (myItem != null) {
            return myItem;
        } else {
            return null;
        }
    }

    /**
     * Checks if the room is an empty space.
     * @return True if the room is empty, false otherwise.
     */
    public Boolean isEmptySpace() {
        if (myMonster == null && myItem == null && myHero == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns an image view representing the content of the room.
     * If the hero is in the room, it returns the hero's image.
     * If there is a monster in the room, it returns the monster's image.
     * If there is an item in the room, it returns the item's image.
     * If the room is a wall, it returns a wall image.
     * Otherwise, it returns an image representing an empty floor tile.
     * @return An ImageView representing the content of the room.
     */
    public ImageView getImage() {
        if (CharacterWindow.myHero.getRoom().getXLocation() == myXLocation && CharacterWindow.myHero.getRoom().getYLocation() == myYLocation) {
            if (CharacterWindow.myHero.getHeroClass().equalsIgnoreCase("Priestess")) {
                Image heroImage = new Image("Priestess.png");
                ImageView heroImageView = new ImageView(heroImage);
                heroImageView.setFitWidth(25);
                heroImageView.setPreserveRatio(true);

                return heroImageView;
            } else if (CharacterWindow.myHero.getHeroClass().equalsIgnoreCase("Warrior")) {
                Image heroImage = new Image("Warrior.png");
                ImageView heroImageView = new ImageView(heroImage);
                heroImageView.setFitWidth(25);
                heroImageView.setPreserveRatio(true);

                return heroImageView;
            } else if (CharacterWindow.myHero.getHeroClass().equalsIgnoreCase("Thief")) {
                Image heroImage = new Image("Thief.png");
                ImageView heroImageView = new ImageView(heroImage);
                heroImageView.setFitWidth(25);
                heroImageView.setPreserveRatio(true);

                return heroImageView;
            } else {
                return null;
            }
        } else if (myMonster != null) {
            return myMonster.getImage();
        } else if (myItem != null) {
            return myItem.getImage();
        } else {

            if (myIsWall) {
                Image wall = new Image("Wall.png");
                ImageView wallImageView = new ImageView(wall);
                wallImageView.setFitWidth(25);
                wallImageView.setPreserveRatio(true);

                return wallImageView;
            } else {
                Image emptySpace = new Image("Floor-Tile.png");
                ImageView emptySpaceImageView = new ImageView(emptySpace);
                emptySpaceImageView.setFitWidth(25);
                emptySpaceImageView.setPreserveRatio(true);

                return emptySpaceImageView;
            }
        }
    }

    /**
     * Sets the visit status of the room.
     * @param theIsVisited True if the room has been visited, false otherwise.
     */
    public void setIsVisited(final Boolean theIsVisited) {
        this.myIsVisited = theIsVisited;
    }

    /**
     * Sets the top neighbor room of the current room.
     * @param neighbor The room to set as the top neighbor.
     */
    public void topNeighbor(final Room neighbor) {
        if (neighbor != null) {
            this.myTopNeighbor = neighbor;
        }
    }

    /**
     * Returns the top neighbor room of the current room.
     * @return The top neighbor room, or null if there is no top neighbor.
     */
    public Room getTopNeighbor() {
        return this.myTopNeighbor;
    }

    /**
     * Sets the bottom theNeighbor room of the current room.
     * @param theNeighbor The room to set as the bottom theNeighbor.
     */
    public void bottomNeighbor(final Room theNeighbor) {
        if (theNeighbor != null) {
            this.myBottomNeighbor = theNeighbor;
        }
    }

    /**
     * Returns the bottom neighbor room of the current room.
     * @return The bottom neighbor room, or null if there is no bottom neighbor.
     */
    public Room getBottomNeighbor() {
        return this.myBottomNeighbor;
    }

    /**
     * Sets the left theNeighbor room of the current room.
     * @param theNeighbor The room to set as the left theNeighbor.
     */
    public void leftNeighbor(final Room theNeighbor) {
        if (theNeighbor != null) {
            this.myLeftNeighbor = theNeighbor;
        }
    }

    /**
     * Returns the left neighbor room of the current room.
     * @return The left neighbor room, or null if there is no left neighbor.
     */
    public Room getLeftNeighbor() {
        return this.myLeftNeighbor;
    }

    /**
     * Sets the right theNeighbor room of the current room.
     * @param theNeighbor The room to set as the right theNeighbor.
     */
    public void rightNeighbor(final Room theNeighbor) {
        if (theNeighbor != null) {
            this.myRightNeighbor = theNeighbor;
        }
    }

    /**
     * Returns the right neighbor room of the current room.
     * @return The right neighbor room, or null if there is no right neighbor.
     */
    public Room getRightNeighbor() {
        return this.myRightNeighbor;
    }

    /**
     * Sets the visit status of the room.
     * @param theVisited True if the room has been visited, false otherwise.
     */
    public void setVisited(final boolean theVisited) {
        this.myIsVisited = theVisited;
    }

    /**
     * Gets the visit status of the room.
     * @return The visit status.
     */
    public boolean getIsVisited() {
        return this.myIsVisited;
    }

    /**
     * Checks if the room is a wall.
     * @return True if the room is a wall, false otherwise.
     */
    public boolean isWall() {
        return this.myIsWall;
    }
    /**
     * Sets the wall status of the room.
     * @param theIsWall True if the room is a wall, false otherwise.
     */
    public void setIsWall(final boolean theIsWall) {
        this.myIsWall = theIsWall;
    }

    /**
     * Checks if the room is visible to the player.
     * @return True if the room is visible, false otherwise.
     */
    public boolean isVisible() {
        return myIsVisible;
    }

    /**
     * Sets the visibility status of the room.
     * @param theVisible True if the room is visible, false otherwise.
     */
    public void setVisible(final boolean theVisible) {
        myIsVisible = theVisible;
    }
}
