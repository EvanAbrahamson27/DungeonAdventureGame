package model;

import controller.DungeonAdventure;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.CharacterWindow;

import java.io.Serializable;

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

    public Room(final Monster theMonster, final Hero theHero, final Item theItem, final int theXLocation, final int theYLocation, final boolean isWall) {
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

    public int getXLocation() {
        return myXLocation;
    }

    public int getYLocation() {
        return myYLocation;
    }

    public void encounterMonster() {
        if (myMonster != null && !myMonster.getIsDead()) {
            DungeonAdventure.myMonster = myMonster;
            CharacterWindow.myHero.startBattle(DungeonAdventure.myMonster);
        }
    }

    public void updateMonsterDeath() {
        if (myMonster != null && myMonster.getIsDead()) {
            myMonster = null;
        }
    }

    public void encounterItem() {
        if (myItem != null) {
            CharacterWindow.myHero.addToInventory(myItem);
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

    public void setIsVisited(final Boolean theIsVisited) {
        this.myIsVisited = theIsVisited;
    }

    public void topNeighbor(final Room neighbor) {
        if (neighbor != null) {
            this.myTopNeighbor = neighbor;
        }
    }

    public Room getTopNeighbor() {
        return this.myTopNeighbor;
    }

    public void bottomNeighbor(final Room neighbor) {
        if (neighbor != null) {
            this.myBottomNeighbor = neighbor;
        }
    }

    public Room getBottomNeighbor() {
        return this.myBottomNeighbor;
    }

    public void leftNeighbor(final Room neighbor) {
        if (neighbor != null) {
            this.myLeftNeighbor = neighbor;
        }
    }

    public Room getLeftNeighbor() {
        return this.myLeftNeighbor;
    }

    public void rightNeighbor(final Room neighbor) {
        if (neighbor != null) {
            this.myRightNeighbor = neighbor;
        }
    }

    public Room getRightNeighbor() {
        return this.myRightNeighbor;
    }

    public void setVisited(final boolean theVisited) {
        this.myIsVisited = theVisited;
    }

    public boolean getIsVisited() {
        return this.myIsVisited;
    }

    public boolean isWall() {
        return this.myIsWall;
    }
    public void setIsWall(final boolean theIsWall) {
        this.myIsWall = theIsWall;
    }

    public boolean isVisible() {
        return myIsVisible;
    }

    public void setVisible(final boolean theVisible) {
        myIsVisible = theVisible;
    }
}
