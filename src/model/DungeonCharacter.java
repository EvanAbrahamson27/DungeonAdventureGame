package model;

import controller.DungeonAdventure;

import java.util.Random;

public abstract class DungeonCharacter {
    final protected String myName;
    protected int myHealthPoints;
    protected int myDamageMin;
    protected int myDamageMax;
    protected double myAttackSpeed;
    protected double myChanceToHit;
    protected int myTurns;
    protected boolean myIsDead;
    protected Position myPosition;

    DungeonCharacter(String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
                     double theChanceToHit) {
        myName = theName;
        myHealthPoints = theHealthPoints;
        myDamageMin = theDamageMin;
        myDamageMax = theDamageMax;
        myAttackSpeed = theAttackSpeed;
        myChanceToHit = theChanceToHit;
        myIsDead = false;
        myPosition = new Position(0,0);
    }

    public void attack(DungeonCharacter theCh) {
        if (!myIsDead && !theCh.getIsDead()) {
            Random r = new Random();
            if (myTurns > 0) {
                if (r.nextInt(101) < this.myChanceToHit) {
                    int damageDealt = r.nextInt(myDamageMax - myDamageMin) + myDamageMin;
                    theCh.takeDamage(damageDealt);
                } else {
                    DungeonAdventure.addToLog(myName + " missed attack!");
                }
                myTurns--;
            }
            if (myTurns <= 0) {
                theCh.startBattle(this);
                if (theCh.getClass().equals(Monster.class)) DungeonAdventure.myMonster.useTurn();
            }
        }
    }

    public void takeDamage(int theDamage) {
        if (!myIsDead) {
            this.myHealthPoints -= theDamage;
            String message = ("Ouch! " + myName + " took " + theDamage + " damage!");
            if (this.getClass().equals(Monster.class) && this.myHealthPoints > 0) {
                message += (" (" + myHealthPoints + " remain)");
            }
            DungeonAdventure.addToLog(message);
            if (this.myHealthPoints <= 0) {
                die();
            }
        }
    }

    public void heal(int theHealAmt) {
        if (!myIsDead) {
            this.myHealthPoints += theHealAmt;
            DungeonAdventure.addToLog(myName + " has been healed for " + theHealAmt + " damage!");
        }
    }

    public void startBattle(DungeonCharacter theCh) {
        if (!myIsDead && !theCh.getIsDead()) {
            myTurns = (int)(this.myAttackSpeed / theCh.myAttackSpeed);
            if (myTurns == 0) myTurns++;
        }
    }

    public void die() {
        myIsDead = true;
        DungeonAdventure.addToLog(myName + " has died.");
    }

    public int getHealthPoints() {
        return myHealthPoints;
    }

    public boolean getIsDead() {
        return myIsDead;
    }

    @Override
    public String toString() {
        return myName;
    }
}
