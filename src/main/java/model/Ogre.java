/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */
package model;

public class Ogre extends Monster {

    /**
     * The Ogre class represents a ogre monster in the game.
     * It extends the Monster class, inheriting its properties and methods, and defines the specific attributes for an Ogre.
     */
    public Ogre(String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
                    double theChanceToHit, double theChanceToHeal, int theMinHealPoints, int theMaxHealPoints,
                    int theX, int theY) {
        super("Ogre", 200, 30, 60, 2,
                0.6, 0.1, 30, 60, theX, theY, "ogre.png");
    }

}
