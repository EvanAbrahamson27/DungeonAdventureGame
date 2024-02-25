package com.model;

import com.controller.DungeonAdventure;

public class Room {
    private final Monster myMonster;
    private Item myItem;
    private final int myXLocation;
    private final int myYLocation;

    public Room(final Monster theMonster, final Item theItem, final int theXLocation, final int theYLocation) {
        myMonster = theMonster;
        myItem = theItem;
        myXLocation = theXLocation;
        myYLocation = theYLocation;
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

    public void encounterItem() {
        if (myItem != null) {
            DungeonAdventure.myHero.addToInventory(myItem);
            myItem = null;
        }
    }

    public Item getItem() {
        return myItem;
    }
}
