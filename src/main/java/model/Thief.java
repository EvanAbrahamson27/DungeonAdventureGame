package model;

import controller.DungeonAdventure;

import java.util.Random;

public class Thief extends Hero {
    final static int DAMAGE_MIN = 20;
    final static int DAMAGE_MAX = 40;

    public Thief (final String theName, final int theHealthPoints, final int theDamageMin, final int theDamageMax,
                  final int theAttackSpeed, final double theChanceToHit, final int theChanceToBlock, final int theX, final int theY) {
//        super(theName, 75, DAMAGE_MIN, DAMAGE_MAX, 6, 80);
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit, theChanceToBlock, theX, theY);
        setSkillName("Surprise Attack");
    }

    public void performSpecialSkill() {
        DungeonAdventure.addToLog(getName() + " used skill: " + getSkillName() + "!");
        int attackChance = new Random().nextInt(101);
        if (attackChance >= 60) {
            DungeonAdventure.addToLog("Success! Extra turn!");
            setTurns(getTurns() + 2);
            attack(DungeonAdventure.myMonster);
        } else if (attackChance >= 20) {
            DungeonAdventure.addToLog("Caught. Normal attack!");
            setTurns(getTurns() + 1);
            attack(DungeonAdventure.myMonster);
        } else {
            setTurns(0);
            DungeonAdventure.addToLog("Missed skill!");
        }

        setSkillCooldown(1);
        if (getTurns() == 0) {
            DungeonAdventure.myMonster.startBattle(this);
            DungeonAdventure.myMonster.useTurn();
        }
    }

    public String getHeroClass() {
        return "Thief";
    }
}
