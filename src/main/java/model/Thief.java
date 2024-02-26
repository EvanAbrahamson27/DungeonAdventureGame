package model;

import controller.DungeonAdventure;

import java.util.Random;

public class Thief extends Hero {
    final static int DAMAGE_MIN = 20;
    final static int DAMAGE_MAX = 40;

    public Thief (String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
                  double theChanceToHit, int theX, int theY) {
//        super(theName, 75, DAMAGE_MIN, DAMAGE_MAX, 6, 80);
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit, theX, theY);
        setSkillName("Surprise Attack");
    }

    public void performSpecialSkill() {
        DungeonAdventure.addToLog(myName + " used skill: " + getSkillName() + "!");
        int attackChance = new Random().nextInt(101);
        if (attackChance >= 60) {
            DungeonAdventure.addToLog("Success! Extra turn!");
            myTurns += 2;
            attack(DungeonAdventure.myMonster);
        } else if (attackChance >= 20) {
            DungeonAdventure.addToLog("Caught. Normal attack!");
            myTurns ++;
            attack(DungeonAdventure.myMonster);
        } else {
            myTurns = 0;
            DungeonAdventure.addToLog("Missed skill!");
        }

        setSkillCooldown(1);
        if (myTurns == 0) {
            DungeonAdventure.myMonster.startBattle(this);
            DungeonAdventure.myMonster.useTurn();
        }
    }

    public String getHeroClass() {
        return "Thief";
    }
}
