/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */
package model;

import controller.DungeonAdventure;
import java.util.Random;

/**
 * Represents a Thief character in the game.
 * Inherits attributes and behaviors from the Hero class.
 */
public class Thief extends Hero {

    /** The minimum damage the Thief can deal. */
    final static int DAMAGE_MIN = 20;
    /** The maximum damage the Thief can deal. */

    final static int DAMAGE_MAX = 40;

    /**
     * Constructs a new Thief character with the specified attributes.
     * @param theName The name of the Thief.
     * @param theHealthPoints The health points of the Thief.
     * @param theDamageMin The minimum damage the Thief can deal.
     * @param theDamageMax The maximum damage the Thief can deal.
     * @param theAttackSpeed The attack speed of the Thief.
     * @param theChanceToHit The chance to hit of the Thief.
     * @param theChanceToBlock The chance to block attacks of the Thief.
     * @param theX The X-coordinate of the starting position of the Thief.
     * @param theY The Y-coordinate of the starting position of the Thief.
     */
    public Thief (final String theName, final int theHealthPoints, final int theDamageMin, final int theDamageMax,
                  final int theAttackSpeed, final double theChanceToHit, final int theChanceToBlock, final int theX, final int theY) {
//        super(theName, 75, DAMAGE_MIN, DAMAGE_MAX, 6, 80);
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit, theChanceToBlock, theX, theY);
        setSkillName("Surprise Attack");
    }

    /**
     * Performs the Thief's special skill, the Surprise Attack.
     * Depending on the outcome of a random chance, the Thief may get an extra turn, a normal attack,
     * or miss the skill.
     * If the turns are exhausted, it starts the monster's turn.
     */
    public void performSpecialSkill() {
        DungeonAdventure.addToLog(getName() + " used skill: " + getSkillName() + "!");
        int attackChance = new Random().nextInt(101);
        if (attackChance >= 60) {
            DungeonAdventure.addToLog("Success! Extra turn!");
            setTurns(getTurns() + 2);
            attack(DungeonAdventure.myMonster);
        } else if (attackChance >= 20) {
            DungeonAdventure.addToLog("Caught. Normal attack!");
            setTurns(getTurns() + 1);
            attack(DungeonAdventure.myMonster);
        } else {
            setTurns(0);
            DungeonAdventure.addToLog("Missed skill!");
        }

        setSkillCooldown(1);
        if (getTurns() == 0) {
            DungeonAdventure.myMonster.startBattle(this);
            DungeonAdventure.myMonster.useTurn();
        }
    }


    /**
     * Returns the class of the Thief character.
     * @return The class of the Thief character.
     */
    public String getHeroClass() {
        return "Thief";
    }
}
