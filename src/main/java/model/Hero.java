/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */
package model;

import controller.DungeonAdventure;
import view.CharacterWindow;
import view.GameWindow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * The Hero class extends DungeonCharacter and represents the player's character in the game.
 * It includes properties such as inventory, current room, skill name, skill cooldown, hero class, and number of collected pillars.
 * Additionally, the class provides methods for moving the hero, using items, blocking attacks, and performing a special skill.
 */
public class Hero extends DungeonCharacter implements Serializable {
    private final List<Item> myInventory;
    private Room myRoom;
    private String mySkillName;
    private int mySkillCooldown = 0;
    private String heroClass = "";
    private int myPillars = 0;
    private final int myChanceToBlock;

    /**
     * Constructs a Hero with specified attributes and initializes inventory and current room.
     * @param theName Name of the hero.
     * @param theHealthPoints Initial health points.
     * @param theDamageMin Minimum damage.
     * @param theDamageMax Maximum damage.
     * @param theAttackSpeed Attack speed.
     * @param theChanceToHit Chance to hit the opponent.
     * @param theChanceToBlock Chance to block an incoming attack.
     * @param theX Initial X-coordinate in the dungeon.
     * @param theY Initial Y-coordinate in the dungeon.
     */
    public Hero(final String theName, final int theHealthPoints, final int theDamageMin, final int theDamageMax,
                final int theAttackSpeed, final double theChanceToHit, final int theChanceToBlock, final int theX, final int theY) {
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit);
        myInventory = new ArrayList<>();
        myChanceToBlock = theChanceToBlock;
        myRoom = new Room(null, this, null, theX, theY, false);
        mySkillName = "Heal";
    }

    /**
     * Sets the current room of the hero.
     * @param theRoom The new room for the hero.
     */
    public void setRoom(final Room theRoom) {
        myRoom = theRoom;
        showHeroRooms();
    }

    /**
     * Attempts to block an attack using the hero's chance to block.
     * @return true if the attack is successfully blocked, false otherwise.
     */
    public boolean blockAttack() {
        Random r = new Random();
        if (r.nextInt(101) < myChanceToBlock) {
            DungeonAdventure.addToLog("Blocked attack!");
            return true;
        }
        return false;
    }

    /**
     * Uses an item from the hero's inventory.
     * @param theItem The name of the item to use.
     * @param theTarget The target to apply the item on.
     */
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

    /**
     * Adds an item to the hero's inventory.
     * @param theItem The item to add.
     */
    public void addToInventory(final Item theItem) {
        myInventory.add(theItem);
        DungeonAdventure.addToLog("Picked up a " + theItem);
        if (theItem.getMyItemType() == 'p') {
            theItem.usePillar();
            myInventory.remove(theItem);
        }
    }

    /**
     * Gets the hero's inventory.
     * @return A list of items in the hero's inventory.
     */
    public List<Item> getInventory() {
        return myInventory;
    }

    /**
     * Returns a string representation of the Hero, including name, health, attack range,
     * attack speed, chance to hit, class, special skill, number of collected pillars, and items.
     * This method is intended for providing a detailed overview of the hero's current status.
     * @return A formatted string containing the hero's current status and attributes.
     */
    @Override
    public String toString() {
        return (getName() +
                "\nHealth: " + getHealthPoints() +
                "\nAttack Range: " + getDamageMin() + " - " + getDamageMax() +
                "\nAttack Speed: " + getAttackSpeed() +
                "\nChance to Hit: " + getChanceToHit() + "%" +
                "\n\nClass: " + this.getClass().toString().substring(12) +
                " \nSpecial Skill: " + mySkillName +
                "\nPillars: " + myPillars +
                "\n\nItems: ");
    }

    /**
     * Retrieves the current room where the hero is located.
     * This can be used to determine the hero's position within the dungeon.
     * @return The Room object representing the hero's current location.
     */
    public Room getRoom() {
        return myRoom;
    }

    /**
     * Moves the hero to a new location in the dungeon.
     * @param theX The X-coordinate of the new location.
     * @param theY The Y-coordinate of the new location.
     */
    public void move(final int theX, final int theY) {
        Room newRoom = CharacterWindow.myDungeonMap.getRoomAtLocation(theX, theY);
        if (newRoom != null && !newRoom.isWall()) {
            myRoom = newRoom;
            DungeonAdventure.addToLog(getName() + " moved to [" + getX() + "," + getY() + "]");
            myRoom.encounterMonster();
            myRoom.encounterItem();
        }
        showHeroRooms();
    }

    /**
     * Performs the hero's special skill.
     */
    public void performSpecialSkill() {
        heal(5);
    }

    /**
     * Retrieves the Y-coordinate of the hero's current location in the dungeon.
     * This method is used to identify the hero's vertical position within the map grid.
     * @return An integer representing the Y-coordinate of the hero's current room.
     */
    public int getY() {
        return myRoom.getYLocation();
    }
    /**
     * Retrieves the X-coordinate of the hero's current location in the dungeon.
     * This method is used to identify the hero's horizontal position within the map grid.
     * @return An integer representing the X-coordinate of the hero's current room.
     */
    public int getX() {
        return myRoom.getXLocation();
    }

    /**
     * Gets the name of the hero's special skill.
     * This method is intended to provide information about the hero's unique ability.
     * @return A string representing the name of the hero's special skill.
     */
    public String getSkillName() {
        return mySkillName;
    }

    /**
     * Sets the hero's special skill name.
     * @param theSkill The new name of the special skill.
     */
    public void setSkillName(final String theSkill) {
        mySkillName = theSkill;
    }

    /**
     * Sets the hero's class.
     * @param theClass The new class of the hero.
     */
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

    /**
     * Gets the hero's class.
     * @return The class of the hero.
     */
    public String getHeroClass() {
        return this.heroClass;
    }

    /**
     * Gets the hero's skill cooldown.
     * @return The skill cooldown.
     */
    public int getSkillCooldown() {
        return mySkillCooldown;
    }

    /**
     * Sets the hero's skill cooldown.
     * @param theSkillCooldown The new skill cooldown.
     */
    public void setSkillCooldown(final int theSkillCooldown) {
        this.mySkillCooldown = theSkillCooldown;
    }


    /**
     * Hero dies and performs necessary actions upon death.
     */
    public void die() {
        setIsDead(true);
        CharacterWindow.myHero.setSkillCooldown(0);
        DungeonAdventure.addToLog(getName() + " has died.");
        if (DungeonAdventure.myMonster != null) DungeonAdventure.myMonster.die();
        GameWindow.openGameOverWindow(true);
    }

    /**
     * Gets the number of pillars the hero has collected.
     * @return The number of pillars.
     */
    public int getPillars() {
        return myPillars;
    }


    /**
     * Sets the number of pillars the hero has collected.
     * @param thePillars The new number of pillars.
     */
    public void setPillars(int thePillars) {
        myPillars = thePillars;
    }

    /**
     * Shows the rooms around the hero.
     */
    public void showHeroRooms() {
        if (CharacterWindow.myHero != null) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (CharacterWindow.myHero.getX() + i >= 0 && CharacterWindow.myHero.getY() + j >= 0 &&
                            CharacterWindow.myHero.getX() + i <= 10 && CharacterWindow.myHero.getY() + j <= 10 &&
                            !CharacterWindow.myDungeonMap.getMap()[CharacterWindow.myHero.getX() + i]
                                    [CharacterWindow.myHero.getY() + j].isVisible()) {
                        CharacterWindow.myDungeonMap.getMap()[CharacterWindow.myHero.getX() + i][
                                CharacterWindow.myHero.getY() + j].setVisible(true);
                    }
                }
            }
        }
    }
}
