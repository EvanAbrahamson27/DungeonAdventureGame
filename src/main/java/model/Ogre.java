package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ogre extends Monster {


    public Ogre(String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
                    double theChanceToHit, double theChanceToHeal, int theMinHealPoints, int theMaxHealPoints, int theX, int theY) {
        super("Ogre", 200, 30, 60, 2,
                0.6, 0.1, 30, 60, theX, theY, "ogre.png");
    }

}
