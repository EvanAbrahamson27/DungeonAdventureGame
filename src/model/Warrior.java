package model;

public class Warrior extends Hero {
    final static int DAMAGE_MIN = 75;
    final static int DAMAGE_MAX = 175;

    public Warrior(String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
                   double theChanceToHit) {
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit);
    }

    public void performSpecialSkill() {

    }

}
