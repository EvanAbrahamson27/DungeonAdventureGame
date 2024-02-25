package com.model;

import com.controller.DungeonAdventure;

import java.util.Random;

public class Warrior extends Hero {
    final static int DAMAGE_MIN = 35;
    final static int DAMAGE_MAX = 60;

    public Warrior(String theName) {
        super(theName, 125, DAMAGE_MIN, DAMAGE_MAX, 4, 80);
        setSkillName("Crushing Blow");
    }

    public void performSpecialSkill() {
        DungeonAdventure.addToLog(myName + " used skill: " + getSkillName() + "!");
        if (new Random().nextInt(101) >= 60) {
            DungeonAdventure.myMonster.takeDamage(new Random().nextInt(101) + 75);
        } else {
            DungeonAdventure.addToLog("Missed skill!");
        }

        setSkillCooldown(1);
        if (myTurns == 0) {
            DungeonAdventure.myMonster.startBattle(this);
            DungeonAdventure.myMonster.useTurn();
        }
    }

}
