package model;

import controller.DungeonAdventure;
import view.CharacterWindow;
import view.GameWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hero extends DungeonCharacter {
    private final List<Item> myInventory;
    private Room myRoom;
    private String mySkillName;
    private int mySkillCooldown = 0;
    private String heroClass = "";
    private int myPillars = 0;
    private final int myChanceToBlock;

    public Hero(final String theName, final int theHealthPoints, final int theDamageMin, final int theDamageMax,
                final int theAttackSpeed, final double theChanceToHit, final int theChanceToBlock, final int theX, final int theY) {
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit);
        myInventory = new ArrayList<>();
        myChanceToBlock = theChanceToBlock;
        myRoom = new Room(null, this, null, theX, theY, false);
        mySkillName = "Heal";
    }

    public void setRoom(final Room theRoom) {
        myRoom = theRoom;
    }

    public boolean blockAttack() {
        Random r = new Random();
        if (r.nextInt(101) < myChanceToBlock) {
            DungeonAdventure.addToLog("Blocked attack!");
            return true;
        }
        return false;
    }
    public void useItem(final String theItem, final DungeonCharacter theTarget) {
        for (Item item : myInventory) {
            if (item.toString().equals(theItem)) {
                DungeonAdventure.addToLog(getName() + " used a " + item + "!");
                item.itemControl(theTarget);
                myInventory.remove(item);
                return;
            }
        }
        DungeonAdventure.addToLog("No item found");
    }
    public void addToInventory(final Item theItem) {
        myInventory.add(theItem);
        DungeonAdventure.addToLog("Picked up a " + theItem);
        if (theItem.getMyItemType() == 'p') {
            theItem.usePillar();
            myInventory.remove(theItem);
        }
    }
    public List<Item> getInventory() {
        return myInventory;
    }

    @Override
    public String toString() {
        return (getName() +
                "\nHealth: " + getHealthPoints() +
                "\nAttack Range: " + getDamageMin() + " - " + getDamageMax() +
                "\nAttack Speed: " + getAttackSpeed() +
                "\nChance to Hit: " + getChanceToHit() + "%" +
                "\n\nClass: " + this.getClass().toString().substring(12) +
                " \nSpecial Skill: " + mySkillName +
                "\n\nItems: ");
    }

    public Room getRoom() {
        return myRoom;
    }
    public void move(final int theX, final int theY) {
        Room newRoom = CharacterWindow.myDungeonMap.getRoomAtLocation(theX, theY);
        if (newRoom != null && !newRoom.isWall()) {
            myRoom = newRoom;
            DungeonAdventure.addToLog(getName() + " moved to [" + getX() + "," + getY() + "]");
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
                CharacterWindow.myHero = new Hero(getName(), 75, 25, 45, 5, 70, 20, getX(), getY());
                this.heroClass = "Warrior";
            }
        }
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
    public void die() {
        setIsDead(true);
        CharacterWindow.myHero.setSkillCooldown(0);
        DungeonAdventure.addToLog(getName() + " has died.");
        if (DungeonAdventure.myMonster != null) DungeonAdventure.myMonster.die();
        GameWindow.openGameOverWindow(true);
    }

    public int getPillars() {
        return myPillars;
    }

    public void setPillars(int thePillars) {
        myPillars = thePillars;
    }
}
