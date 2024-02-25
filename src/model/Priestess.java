package model;

public class Priestess extends Hero {
    final static int DAMAGE_MIN = 25;
    final static int DAMAGE_MAX = 45;

    public Priestess (String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
                   double theChanceToHit) {
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit);
    }

    public void performSpecialSkill() {

    }

}
