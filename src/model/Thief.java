package model;

public class Thief extends Hero {
    final static int DAMAGE_MIN = 20;
    final static int DAMAGE_MAX = 40;

    public Thief (String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
                   double theChanceToHit) {
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit);
    }

    public void performSpecialSkill() {

    }

}
