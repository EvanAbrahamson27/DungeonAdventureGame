package model;

import java.util.Random;

public class DungeonCharacter {
    final private String myName;
    private int myHealthPoints;
    private int myDamageMin;
    private int myDamageMax;
    private double myAttackSpeed;
    private double myChanceToHit;
    private int myTurns;

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
        int totalDamage = 0;
        while (myTurns > 1) {
            if (r.nextInt(101) < this.myChanceToHit) {
                int damageDealt = r.nextInt(myDamageMax - myDamageMin) + myDamageMin;
                theCh.takeDamage(damageDealt);
                totalDamage += damageDealt;
            } else {
                System.out.println("Missed attack!");
            }
        }
        return totalDamage;
    }

    public void takeDamage(int theDamage) {
        this.myHealthPoints -= theDamage;
    }

    public void heal(int theHealAmt) {
        this.myHealthPoints += theHealAmt;
    }

    public void startBattle(DungeonCharacter theCh) {
        myTurns = (int)(this.myAttackSpeed / theCh.myAttackSpeed);
    }
}
