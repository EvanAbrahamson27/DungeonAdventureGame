package com.model;

import com.controller.DungeonAdventure;

import java.util.Random;

public class Monster extends DungeonCharacter {
    private double myChanceToHeal;
    private int myHealMin;
    private int myHealMax;
    private char usingTurn = 'n';
    public Monster(String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
                double theChanceToHit, double theChanceToHeal) {
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit);
    }

    public void useTurn() {
        if (!myIsDead) {
            while (myTurns > 0) {
                Random movePicker = new Random();
                switch (movePicker.nextInt(3)) {
                    case 0, 1 -> {attack(DungeonAdventure.myHero);usingTurn = 'a';}
                    case 2 -> {heal(5);myTurns--;usingTurn = 'h';}
                }
            }
            DungeonAdventure.myHero.setSkillCooldown(DungeonAdventure.myHero.getSkillCooldown() - 1);
            DungeonAdventure.myHero.startBattle(this);
        }
    }

    public char getUsingTurn() {
        return usingTurn;
    }
    public void setUsingTurn(char theChar) {
        usingTurn = theChar;
    }
}
