package model;

import controller.DungeonAdventure;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.CharacterWindow;

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
    private Boolean isWall;

    public Room(final Monster theMonster, final Hero theHero, final Item theItem, final int theXLocation, final int theYLocation, final boolean isWall) {
        myMonster = theMonster;
        myItem = theItem;
        myXLocation = theXLocation;
        myYLocation = theYLocation;
        this.isVisited = false;
        this.topNeighbor = null;
        this.bottomNeighbor = null;
        this.leftNeighbor = null;
        this.rightNeighbor = null;
        this.isWall = isWall;
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

            if (isWall) {
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

    public void setVisited(boolean visited) {
        this.isVisited = visited;
    }

    public boolean getIsVisited() {
        return this.isVisited;
    }

    public boolean isWall() {
        return this.isWall;
    }
    public void setIsWall(boolean isWall) {
        this.isWall = isWall;
    }
}
