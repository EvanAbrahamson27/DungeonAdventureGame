/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */
package model;

/**
 * The Gremlin class represents a gremlin monster in the game.
 * It extends the Monster class, inheriting its properties and methods, and defines the specific attributes for a gremlin.
 */
public class Gremlin extends Monster {
    /**
     * Constructs a new Gremlin with specified location and default attributes.
     * Attributes include name, health points, damage range, attack speed, chance to hit, chance to heal,
     * minimum and maximum heal points, and the X,Y coordinates in the dungeon.
     * @param theName Name of the gremlin.
     * @param theHealthPoints Health points of the gremlin.
     * @param theDamageMin Minimum damage the gremlin can inflict.
     * @param theDamageMax Maximum damage the gremlin can inflict.
     * @param theAttackSpeed Attack speed of the gremlin.
     * @param theChanceToHit Chance for the gremlin's attack to hit the opponent.
     * @param theChanceToHeal Chance for the gremlin to heal itself in battle.
     * @param theMinHealPoints Minimum heal points the gremlin can recover.
     * @param theMaxHealPoints Maximum heal points the gremlin can recover.
     * @param theX X-coordinate of the gremlin in the dungeon.
     * @param theY Y-coordinate of the gremlin in the dungeon.
     */
    public Gremlin(String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
                double theChanceToHit, double theChanceToHeal, int theMinHealPoints, int theMaxHealPoints, int theX, int theY) {
        super("Gremlin", 70, 15, 30, 5,
                0.8, 0.4, 20, 40, theX, theY, "gremlin.png");
    }

}
