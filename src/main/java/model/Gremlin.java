package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Gremlin extends Monster {
    public Gremlin(String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
                double theChanceToHit, double theChanceToHeal, int theMinHealPoints, int theMaxHealPoints, int theX, int theY) {
        super("Gremlin", 70, 15, 30, 5,
                0.8, 0.4, 20, 40, theX, theY, "gremlin.png");
    }

}
