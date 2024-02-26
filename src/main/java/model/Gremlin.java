package model;

public class Gremlin extends Monster {
    public Gremlin(String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
                   double theChanceToHit, double theChanceToHeal, int theX, int theY) {
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit, theChanceToHeal, theX, theY);
    }
}
