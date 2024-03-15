package model;

import controller.DungeonAdventure;
import view.CharacterWindow;
import view.GameWindow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hero extends DungeonCharacter implements Serializable {
    private final List<Item> myInventory;
    private Room myRoom;
    private String mySkillName;
    private int mySkillCooldown = 0;
    private String heroClass = "";

    public Hero(String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
                double theChanceToHit, int theX, int theY) {
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit);
        myInventory = new ArrayList<>();
        myRoom = new Room(null, this, null, theX, theY, false);
        mySkillName = "Heal";
    }

    public void setRoom(Room theRoom) {
        myRoom = theRoom;
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
        return (myName +
                "\nHealth: " + getHealthPoints() +
                "\nAttack Range: " + myDamageMin + " - " + myDamageMax +
                "\nAttack Speed: " + myAttackSpeed +
                "\nChance to Hit: " + myChanceToHit + "%" +
                "\n\nClass: " + this.getClass().toString().substring(12) +
                " \nSpecial Skill: " + mySkillName +
                "\n\nItems: ");
    }

    public Room getRoom() {
        return myRoom;
    }
    public void move(final int theX, final int theY) {
        Room newRoom = CharacterWindow.myDungeonMap.getRoomAtLocation(theX, theY);
        if (newRoom != null && newRoom.isWall() == false) {
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
                this.heroClass = "Priestess";
            } case "Warrior" : {
                this.heroClass = "Warrior";
            } case "Thief" : {
                this.heroClass = "Thief";
            }
            default: {
                CharacterWindow.myHero = new Hero(myName, 75, 25, 45, 5, 70, getX(), getY());
                this.heroClass = "Warrior";
            }
        }
        // this will be used for debug/test menu purposes, not working as intended yet
    }

    public String getHeroClass() {
        return this.heroClass;
    }

    public int getSkillCooldown() {
        return mySkillCooldown;
    }

    public void setSkillCooldown(final int theSkillCooldown) {
        this.mySkillCooldown = theSkillCooldown;
    }

    public void setDamageRange(final int theMinDmg, final int theMaxDmg) {
        myDamageMin = theMinDmg;
        myDamageMax = theMaxDmg;
    }

    public void die() {
        myIsDead = true;
        CharacterWindow.myHero.setSkillCooldown(0);
        DungeonAdventure.addToLog(myName + " has died.");
        if (DungeonAdventure.myMonster != null) DungeonAdventure.myMonster.die();
        GameWindow.openGameOverWindow();
    }
}
