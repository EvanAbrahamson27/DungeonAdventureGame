package model;

import controller.DungeonAdventure;

import java.util.ArrayList;
import java.util.List;

public class Hero extends DungeonCharacter {
    private final List<Item> myInventory;
    private Room myRoom;
    private String mySkillName;
    private int mySkillCooldown = 0;

    public Hero(String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
                     double theChanceToHit) {
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit);
        myInventory = new ArrayList<>();
        myRoom = DungeonAdventure.myDungeonMap.getRoomAtLocation(0, 0);
        mySkillName = "Heal";
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
                "\n\nClass: " + this.getClass().toString().substring(12) + " \nSpecial Skill: Self Heal" +
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
    public void performSpecialSkill() {
        heal(5);
    }
    public String getSkillName() {
        return mySkillName;
    }
    public void setSkillName(final String theSkill) {
        mySkillName = theSkill;
    }
    public void setClass(final String theClass) {

        switch (theClass) {
            case "Priestess" : {
                DungeonAdventure.myHero = new Priestess(myName, myHealthPoints, myDamageMin, myDamageMax, myAttackSpeed, myChanceToHit);
            } case "Warrior" : {
                DungeonAdventure.myHero = new Warrior(myName, myHealthPoints, myDamageMin, myDamageMax, myAttackSpeed, myChanceToHit);
            } case "Thief" : {
                DungeonAdventure.myHero = new Thief(myName, myHealthPoints, myDamageMin, myDamageMax, myAttackSpeed, myChanceToHit);
            }
        }

        // this will be used for debug/test menu purposes, not working as intended yet
    }

    public int getSkillCooldown() {
        return mySkillCooldown;
    }

    public void setSkillCooldown(final int theSkillCooldown) {
        this.mySkillCooldown = theSkillCooldown;
    }
}
