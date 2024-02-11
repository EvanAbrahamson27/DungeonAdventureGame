package model;

import controller.DungeonAdventure;

import java.util.Random;

public class Monster extends DungeonCharacter {
    private double myChanceToHeal;
    private int myHealMin;
    private int myHealMax;
    public Monster(String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
                double theChanceToHit, double theChanceToHeal) {
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit);
    }

    public void useTurn() {
        if (!myIsDead) {
            while (myTurns > 0) {
                Random movePicker = new Random();
                switch (movePicker.nextInt(3)) {
                    case 0, 1 -> attack(DungeonAdventure.myHero);
                    case 2 -> heal(5);
                }
            }
        }
    }

}
