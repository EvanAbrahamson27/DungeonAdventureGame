package model;

import controller.DungeonAdventure;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Room {
    private Monster myMonster;
    private Hero myHero;
    private Item myItem;
    private final int myXLocation;
    private final int myYLocation;
    private boolean isVisited;
    private Room topNeighbor;
    private Room bottomNeighbor;
    private Room leftNeighbor;
    private Room rightNeighbor;

    public Room(final Monster theMonster, final Hero theHero, final Item theItem, final int theXLocation, final int theYLocation) {
        myMonster = theMonster;
        myItem = theItem;
        myXLocation = theXLocation;
        myYLocation = theYLocation;
        this.isVisited = false;
        this.topNeighbor = null;
        this.bottomNeighbor = null;
        this.leftNeighbor = null;
        this.rightNeighbor = null;
    }

    public int getXLocation() {
        return myXLocation;
    }

    public int getYLocation() {
        return myYLocation;
    }

    public void encounterMonster() {
        if (myMonster != null && !myMonster.getIsDead()) {
            DungeonAdventure.myMonster = myMonster;
            DungeonAdventure.myHero.startBattle(DungeonAdventure.myMonster);
        }
    }

    public void updateMonsterDeath() {
        if (myMonster != null && myMonster.getIsDead()) {
            myMonster = null;
        }
    }

    public void encounterItem() {
        if (myItem != null) {
            DungeonAdventure.myHero.addToInventory(myItem);
            myItem = null;
        }
    }

    public Monster getMonster() {
        if (myMonster != null) {
            return myMonster;
        } else {
            return null;
        }
    }

    public Item getItem() {
        if (myItem != null) {
            return myItem;
        } else {
            return null;
        }
    }

    public Boolean isEmptySpace() {
        if (myMonster == null && myItem == null && myHero == null) {
            return true;
        } else {
            return false;
        }
    }

    public ImageView getImage() {
        if (DungeonAdventure.myHero.getRoom().getXLocation() == myXLocation && DungeonAdventure.myHero.getRoom().getYLocation() == myYLocation) {
            Image emptySpace = new Image("Warrior.png");
            ImageView emptySpaceImageView = new ImageView(emptySpace);
            emptySpaceImageView.setFitWidth(50);
            emptySpaceImageView.setPreserveRatio(true);

            return emptySpaceImageView;
        } else if (myMonster != null) {
            return myMonster.getImage();
        } else if (myItem != null) {
            return myItem.getImage();
        } else {
            Image emptySpace = new Image("Floor-Tile.png");
            ImageView emptySpaceImageView = new ImageView(emptySpace);
            emptySpaceImageView.setFitWidth(50);
            emptySpaceImageView.setPreserveRatio(true);

            return emptySpaceImageView;
        }
    }

    public void setIsVisited(Boolean theIsVisited) {
        this.isVisited = theIsVisited;
    }

    public void topNeighbor(Room neighbor) {
        if (neighbor != null) {
            this.topNeighbor = neighbor;
        }
    }

    public Room getTopNeighbor() {
        return this.topNeighbor;
    }

    public void bottomNeighbor(Room neighbor) {
        if (neighbor != null) {
            this.bottomNeighbor = neighbor;
        }
    }

    public Room getBottomNeighbor() {
        return this.bottomNeighbor;
    }

    public void leftNeighbor(Room neighbor) {
        if (neighbor != null) {
            this.leftNeighbor = neighbor;
        }
    }

    public Room getLeftNeighbor() {
        return this.leftNeighbor;
    }

    public void rightNeighbor(Room neighbor) {
        if (neighbor != null) {
            this.rightNeighbor = neighbor;
        }
    }

    public Room getRightNeighbor() {
        return this.rightNeighbor;
    }
}
