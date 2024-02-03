package model;

import java.util.Random;

public class DungeonCharacter {
    final private String myName;
    private int myHealthPoints;
    private int myDamageMin;
    private int myDamageMax;
    private double myAttackSpeed;
    private double myChanceToHit;

    DungeonCharacter(String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
                     double theChanceToHit) {
        myName = theName;
        myHealthPoints = theHealthPoints;
        myDamageMin = theDamageMin;
        myDamageMax = theDamageMax;
        myAttackSpeed = theAttackSpeed;
        myChanceToHit = theChanceToHit;
    }

    public int attack(DungeonCharacter theCh) {
        Random r = new Random();
        if (r.nextInt(101) < this.myChanceToHit) {
            int damageDealt = r.nextInt(myDamageMax - myDamageMin) + myDamageMin;
            theCh.takeDamage(damageDealt);
            return damageDealt;
        } else {
            System.out.println("Missed attack!");
            return 0;
        }
    }

    public void takeDamage(int theDamage) {
        this.myHealthPoints -= theDamage;
    }

    public void heal(int theHealAmt) {
        this.myHealthPoints += theHealAmt;
    }
}
