/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */
package model;

import controller.DungeonAdventure;
import view.CharacterWindow;
import view.RoomPanel;

import java.util.Random;
/**
 * The DungeonCharacter class serves as an abstract base for characters in the game.
 * It encapsulates common properties and methods.
 * This class is designed to be extended by specific types of dungeon characters like heroes and monsters.
 */
public abstract class DungeonCharacter {
    final private String myName;
    private int myHealthPoints;
    private int myDamageMin;
    private int myDamageMax;
    private int myAttackSpeed;
    private double myChanceToHit;
    private int myTurns;
    private boolean myIsDead;

    /**
     * Constructor for the DungeonCharacter class. Initializes character attributes.
     * @param theName Character's name.
     * @param theHealthPoints Character's health points.
     * @param theDamageMin Minimum damage the character can inflict.
     * @param theDamageMax Maximum damage the character can inflict.
     * @param theAttackSpeed Character's attack speed.
     * @param theChanceToHit Character's chance to hit the opponent.
     */
    DungeonCharacter(final String theName, final int theHealthPoints, final int theDamageMin, final int theDamageMax,
                     final int theAttackSpeed, final double theChanceToHit) {
        myName = theName;
        myHealthPoints = theHealthPoints;
        myDamageMin = theDamageMin;
        myDamageMax = theDamageMax;
        myAttackSpeed = theAttackSpeed;
        myChanceToHit = theChanceToHit;
        myIsDead = false;
        Position myPosition = new Position(0, 0);
    }

    /**
     * Performs an attack on another dungeon character.
     * @param theCh The target of the attack.
     */
    public void attack(final DungeonCharacter theCh) {
        if (!myIsDead && !theCh.getIsDead()) {
            Random r = new Random();
            if (myTurns > 0) {
                if (r.nextInt(101) < this.myChanceToHit) {
                    int damageDealt = r.nextInt(myDamageMax - myDamageMin) + myDamageMin;
                    theCh.takeDamage(damageDealt);
                } else {
                    DungeonAdventure.addToLog(myName + " missed attack!");
                }
                myTurns--;
            }
            if (myTurns <= 0) {
                theCh.startBattle(this);
                if (theCh instanceof Monster) DungeonAdventure.myMonster.useTurn();
            }
        }
    }
    /**
     * Applies damage to the character.
     * @param theDamage The amount of damage to inflict.
     */
    public void takeDamage(final int theDamage) {
        if (!myIsDead) {
            this.myHealthPoints -= theDamage;
            String message = ("Ouch! " + myName + " took " + theDamage + " damage!");
            if (this.getClass().equals(Monster.class) && this.myHealthPoints > 0) {
                message += (" (" + myHealthPoints + " remain)");
//                RoomPanel.attackAnimation();
            }
            DungeonAdventure.addToLog(message);
            if (this.myHealthPoints <= 0) {
                die();
            }
        }
    }

    /**
     * Heals the character for a specified amount.
     * @param theHealAmt The amount of health to restore.
     */
    public void heal(final int theHealAmt) {
        if (!myIsDead) {
            this.myHealthPoints += theHealAmt;
            DungeonAdventure.addToLog(myName + " has been healed for " + theHealAmt + " damage!");
        }
    }

    /**
     * Initiates a battle between this character and another.
     * @param theCh The opponent in the battle.
     */
    public void startBattle(final DungeonCharacter theCh) {
        if (!myIsDead && !theCh.getIsDead()) {
            myTurns = (this.myAttackSpeed / theCh.myAttackSpeed);
            if (myTurns == 0) myTurns++;
        }
    }

    /**
     * Marks the character as dead.
     */
    public void die() {
        myIsDead = true;
        CharacterWindow.myHero.setSkillCooldown(0);
        DungeonAdventure.addToLog(myName + " has died.");
    }

    // Accessor and mutator methods follow, providing access to private fields and allowing their modification.
    public String getName() {
        return myName;
    }
    public int getHealthPoints() {
        return myHealthPoints;
    }

    public boolean getIsDead() {
        return myIsDead;
    }
    public void setIsDead(final boolean theIsDead) {
        myIsDead = theIsDead;
    }

    public int getTurns() {
        return myTurns;
    }
    public void setTurns(final int theTurns) {
        myTurns = theTurns;
    }
    public void setDamageRange(final int theMinDmg, final int theMaxDmg) {
        myDamageMin = theMinDmg;
        myDamageMax = theMaxDmg;
    }
    public double getChanceToHit() {
        return myChanceToHit;
    }
    public int getDamageMin() {
        return myDamageMin;
    }
    public int getDamageMax() {
        return myDamageMax;
    }
    public int getAttackSpeed() {
        return myAttackSpeed;
    }
    public void setChanceToHit(double theChanceToHit) {
        myChanceToHit = theChanceToHit;
    }
    @Override
    public String toString() {
        return myName;
    }

    public void setAttackSpeed(int theAttackSpeed) {
        myAttackSpeed = theAttackSpeed;
    }
}
