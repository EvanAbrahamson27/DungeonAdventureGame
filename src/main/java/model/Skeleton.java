/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */
package model;

/**
 * The Skeleton class represents a skeleton monster in the game.
 * It extends the Monster class, inheriting its properties and methods, and defines the specific attributes for an Skeleton.
 */
public class Skeleton extends Monster {

    public Skeleton(String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
                    double theChanceToHit, double theChanceToHeal, int theMinHealPoints, int theMaxHealPoints,
                    int theX, int theY) {
        super("Skeleton", 100, 30, 50, 3,
                0.8, 0.3, 30, 50, theX, theY,"Skeleton.png");
    }

}
