package model;

import controller.DungeonAdventure;
import view.CharacterWindow;
import view.RoomPanel;

import java.util.Random;

public abstract class DungeonCharacter {
    final private String myName;
    private int myHealthPoints;
    private int myDamageMin;
    private int myDamageMax;
    private int myAttackSpeed;
    private double myChanceToHit;
    private int myTurns;
    private boolean myIsDead;

    DungeonCharacter(final String theName, final int theHealthPoints, final int theDamageMin, final int theDamageMax,
                     final int theAttackSpeed, final double theChanceToHit) {
        myName = theName;
        myHealthPoints = theHealthPoints;
        myDamageMin = theDamageMin;
        myDamageMax = theDamageMax;
        myAttackSpeed = theAttackSpeed;
        myChanceToHit = theChanceToHit;
        myIsDead = false;
        Position myPosition = new Position(0, 0);
    }

    public void attack(final DungeonCharacter theCh) {
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
                if (theCh instanceof Monster) DungeonAdventure.myMonster.useTurn();
            }
        }
    }

    public void takeDamage(final int theDamage) {
        if (!myIsDead) {
            this.myHealthPoints -= theDamage;
            String message = ("Ouch! " + myName + " took " + theDamage + " damage!");
            if (this.getClass().equals(Monster.class) && this.myHealthPoints > 0) {
                message += (" (" + myHealthPoints + " remain)");
//                RoomPanel.attackAnimation();
            }
            DungeonAdventure.addToLog(message);
            if (this.myHealthPoints <= 0) {
                die();
            }
        }
    }

    public void heal(final int theHealAmt) {
        if (!myIsDead) {
            this.myHealthPoints += theHealAmt;
            DungeonAdventure.addToLog(myName + " has been healed for " + theHealAmt + " damage!");
        }
    }

    public void startBattle(final DungeonCharacter theCh) {
        if (!myIsDead && !theCh.getIsDead()) {
            myTurns = (this.myAttackSpeed / theCh.myAttackSpeed);
            if (myTurns == 0) myTurns++;
        }
    }

    public void die() {
        myIsDead = true;
        CharacterWindow.myHero.setSkillCooldown(0);
        DungeonAdventure.addToLog(myName + " has died.");
    }
    public String getName() {
        return myName;
    }
    public int getHealthPoints() {
        return myHealthPoints;
    }

    public boolean getIsDead() {
        return myIsDead;
    }
    public void setIsDead(final boolean theIsDead) {
        myIsDead = theIsDead;
    }

    public int getTurns() {
        return myTurns;
    }
    public void setTurns(final int theTurns) {
        myTurns = theTurns;
    }
    public void setDamageRange(final int theMinDmg, final int theMaxDmg) {
        myDamageMin = theMinDmg;
        myDamageMax = theMaxDmg;
    }
    public double getChanceToHit() {
        return myChanceToHit;
    }
    public int getDamageMin() {
        return myDamageMin;
    }
    public int getDamageMax() {
        return myDamageMax;
    }
    public int getAttackSpeed() {
        return myAttackSpeed;
    }
    public void setChanceToHit(double theChanceToHit) {
        myChanceToHit = theChanceToHit;
    }
    @Override
    public String toString() {
        return myName;
    }

    public void setAttackSpeed(int theAttackSpeed) {
        myAttackSpeed = theAttackSpeed;
    }
}
