/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */
package model;

import controller.DungeonAdventure;
import java.util.Random;

/**
 * Represents a Priestess character in the game.
 */
public class Priestess extends Hero {

    /** The minimum damage a Priestess can deal. */
    final static int DAMAGE_MIN = 25;

    /** The maximum damage a Priestess can deal. */
    final static int DAMAGE_MAX = 45;

    /**
     * Constructs a new Priestess character with the specified attributes.
     * @param theName The name of the Priestess.
     * @param theHealthPoints The health points of the Priestess.
     * @param theDamageMin The minimum damage the Priestess can deal.
     * @param theDamageMax The maximum damage the Priestess can deal.
     * @param theAttackSpeed The attack speed of the Priestess.
     * @param theChanceToHit The chance to hit of the Priestess.
     * @param theChanceToBlock The chance to block attacks of the Priestess.
     * @param theX The initial x-coordinate of the Priestess on the map.
     * @param theY The initial y-coordinate of the Priestess on the map.
     */
    public Priestess (final String theName, final int theHealthPoints, final int theDamageMin,
                      final int theDamageMax, final int theAttackSpeed,
                      final double theChanceToHit, final int theChanceToBlock, final int theX, final int theY) {
//        super(theName, 75, DAMAGE_MIN, DAMAGE_MAX, 5, 70, theX, theY);
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit, theChanceToBlock, theX, theY);
        setSkillName("Blessed Heal");
    }

    /**
     * Performs the special skill of the Priestess, which is a healing ability.
     */
    public void performSpecialSkill() {
        if (getTurns() > 0) {
            DungeonAdventure.addToLog(getName() + " used skill: " + getSkillName() + "!");
            heal(new Random().nextInt(DAMAGE_MAX - DAMAGE_MIN + 1) + DAMAGE_MIN);
            setSkillCooldown(2);
            if (getTurns() == 0) {
                DungeonAdventure.myMonster.startBattle(this);
                DungeonAdventure.myMonster.useTurn();
            }
        }
    }

    /**
     * Retrieves the class of the character as "Priestess".
     * @return The class of the character.
     */
    public String getHeroClass() {
        return "Priestess";
    }

}
