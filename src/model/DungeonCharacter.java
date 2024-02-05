package model;

import controller.DungeonAdventure;

import java.util.Random;

public class DungeonCharacter {
    final protected String myName;
    protected int myHealthPoints;
    protected int myDamageMin;
    protected int myDamageMax;
    protected double myAttackSpeed;
    protected double myChanceToHit;
    protected int myTurns;

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
        DungeonAdventure.addToLog("Ouch! " + myName + " took " + theDamage + " damage!");
    }

    public void heal(int theHealAmt) {
        this.myHealthPoints += theHealAmt;
        DungeonAdventure.addToLog(myName + " has been healed for " + theHealAmt + " damage!");
    }

    public void startBattle(DungeonCharacter theCh) {
        myTurns = (int)(this.myAttackSpeed / theCh.myAttackSpeed);
    }

    public int getHealthPoints() {
        return myHealthPoints;
    }

    public int getDamageMin() {
        return myDamageMin;
    }

    public int getDamageMax() {
        return myDamageMax;
    }

    public double getAttackSpeed() {
        return myAttackSpeed;
    }

    public double getChanceToHit() {
        return myChanceToHit;
    }
}
