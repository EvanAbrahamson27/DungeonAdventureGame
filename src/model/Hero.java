package model;

import controller.DungeonAdventure;

import java.util.ArrayList;
import java.util.List;

public class Hero extends DungeonCharacter {
    private final List<Item> myInventory;
    private Room myRoom;

    public Hero(String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
                     double theChanceToHit) {
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit);
        myInventory = new ArrayList<>();
        myRoom = DungeonAdventure.myDungeonMap.getRoomAtLocation(0, 0);
    }

    public void blockAttack() {

    }
    public void useItem(String theItem, DungeonCharacter theTarget) {
        for (Item item : myInventory) {
            if (item.toString().equals(theItem)) {
                DungeonAdventure.addToLog(myName + " used a " + item + "!");
                item.itemControl(theTarget);
                myInventory.remove(item);
                return;
            }
        }
        DungeonAdventure.addToLog("No item found");
    }
    public void addToInventory(Item theItem) {
        myInventory.add(theItem);
        DungeonAdventure.addToLog("Picked up a " + theItem);
    }
    public List<Item> getInventory() {
        return myInventory;
    }

    @Override
    public String toString() {
        return ("Health: " + getHealthPoints() +
                "\nAttack Range: " + myDamageMin + " - " + myDamageMax +
                "\nAttack Speed: " + myAttackSpeed +
                "\nChance to Hit: " + myChanceToHit + "%" +
                "\n\nClass: Hero\nSpecial Skill: Self Heal" +
                "\n\nItems: ");
    }

    public Room getRoom() {
        return myRoom;
    }
    public void move(final int theX, final int theY) {
        Room newRoom = DungeonAdventure.myDungeonMap.getRoomAtLocation(theX, theY);
        if (newRoom != null) {
            myRoom = newRoom;
            DungeonAdventure.addToLog(myName + " moved to [" + getX() + "," + getY() + "]");
            myRoom.encounterMonster();
            myRoom.encounterItem();
        }
    }
    public int getY() {
        return myRoom.getYLocation();
    }
    public int getX() {
        return myRoom.getXLocation();
    }
}
