package model;

import controller.DungeonAdventure;

import java.util.Random;

public class Priestess extends Hero {
    final static int DAMAGE_MIN = 25;
    final static int DAMAGE_MAX = 45;

    public Priestess (String theName) {
        super(theName, 75, DAMAGE_MIN,
                DAMAGE_MAX, 5, 70);
        setSkillName("Blessed Heal");
    }

    public void performSpecialSkill() {
        if (myTurns > 0) {
            DungeonAdventure.addToLog(myName + " used skill: " + getSkillName() + "!");
            heal(new Random().nextInt(DAMAGE_MAX - DAMAGE_MIN + 1) + DAMAGE_MIN);
            setSkillCooldown(2);
            if (myTurns == 0) {
                DungeonAdventure.myMonster.startBattle(this);
                DungeonAdventure.myMonster.useTurn();
            }
        }
    }

}
