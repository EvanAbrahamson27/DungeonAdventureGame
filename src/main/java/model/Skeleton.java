package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Skeleton extends Monster {

    public Skeleton(String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
                    double theChanceToHit, double theChanceToHeal, int theMinHealPoints, int theMaxHealPoints, int theX, int theY) {
        super("Skeleton", 100, 30, 50, 3,
                0.8, 0.3, 30, 50, theX, theY,"Skeleton.png");
    }

}
