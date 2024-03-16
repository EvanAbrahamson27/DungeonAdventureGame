/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */
package model;

import controller.DungeonAdventure;
import java.util.Random;

/**
 * Represents a Warrior character in the game.
 * Inherits attributes and behaviors from the Hero class.
 */
public class Warrior extends Hero {
    /** The minimum damage the Warrior can deal. */

    final static int DAMAGE_MIN = 35;

    /** The maximum damage the Warrior can deal. */
    final static int DAMAGE_MAX = 60;


    /**
     * Constructs a new Warrior character with the specified attributes.
     * @param theName The name of the Warrior.
     * @param theHealthPoints The health points of the Warrior.
     * @param theDamageMin The minimum damage the Warrior can deal.
     * @param theDamageMax The maximum damage the Warrior can deal.
     * @param theAttackSpeed The attack speed of the Warrior.
     * @param theChanceToHit The chance to hit of the Warrior.
     * @param theChanceToBlock The chance to block attacks of the Warrior.
     * @param theX The X-coordinate of the starting position of the Warrior.
     * @param theY The Y-coordinate of the starting position of the Warrior.
     */
    public Warrior(final String theName, final int theHealthPoints, final int theDamageMin, final int theDamageMax,
                   final int theAttackSpeed, final double theChanceToHit, final int theChanceToBlock, final int theX, final int theY) {
//        super(theName, 125, DAMAGE_MIN, DAMAGE_MAX, 4, 80);
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit, theChanceToBlock, theX, theY);
        setSkillName("Crushing Blow");
    }


    /**
     * Performs the Warrior's special skill, the Crushing Blow.
     * The Warrior attempts to deal a high amount of damage to the monster.
     * The success of the attack is determined by a random chance.
     * If the turns are exhausted, it starts the monster's turn.
     */
    public void performSpecialSkill() {
        DungeonAdventure.addToLog(getName() + " used skill: " + getSkillName() + "!");
        if (new Random().nextInt(101) >= 60) {
            DungeonAdventure.myMonster.takeDamage(new Random().nextInt(101) + 75);
        } else {
            DungeonAdventure.addToLog("Missed skill!");
        }

        setSkillCooldown(1);
        if (getTurns() == 0) {
            DungeonAdventure.myMonster.startBattle(this);
            DungeonAdventure.myMonster.useTurn();
        }
    }

    /**
     * Returns the class of the Warrior character.
     * @return The class of the Warrior character.
     */
    public String getHeroClass() {
        return "Warrior";
    }
}
