package model;

import controller.DungeonAdventure;

import java.util.Random;

public class Warrior extends Hero {
    final static int DAMAGE_MIN = 35;
    final static int DAMAGE_MAX = 60;

    public Warrior(final String theName, final int theHealthPoints, final int theDamageMin, final int theDamageMax,
                   final int theAttackSpeed, final double theChanceToHit, final int theChanceToBlock, final int theX, final int theY) {
//        super(theName, 125, DAMAGE_MIN, DAMAGE_MAX, 4, 80);
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit, theChanceToBlock, theX, theY);
        setSkillName("Crushing Blow");
    }

    public void performSpecialSkill() {
        DungeonAdventure.addToLog(getName() + " used skill: " + getSkillName() + "!");
        if (new Random().nextInt(101) >= 60) {
            DungeonAdventure.myMonster.takeDamage(new Random().nextInt(101) + 75);
        } else {
            DungeonAdventure.addToLog("Missed skill!");
        }

        setSkillCooldown(1);
        if (getTurns() == 0) {
            DungeonAdventure.myMonster.startBattle(this);
            DungeonAdventure.myMonster.useTurn();
        }
    }

    public String getHeroClass() {
        return "Warrior";
    }
}
