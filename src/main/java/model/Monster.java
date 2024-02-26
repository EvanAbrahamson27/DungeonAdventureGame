package model;

import controller.DungeonAdventure;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Monster extends DungeonCharacter {
    private double myChanceToHeal;
    private int myHealMin;
    private int myHealMax;
    private char usingTurn = 'n';
    private int myRoomX;
    private int myRoomY;
    public Monster(String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
                   double theChanceToHit, double theChanceToHeal, int theX, int theY) {
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit);
        this.myRoomX = theX;
        this.myRoomY = theY;
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

    public ImageView getImage() {
        Image monsterImage = new Image("Skeleton.png");
        ImageView monsterImageView = new ImageView(monsterImage);
        monsterImageView.setFitWidth(50);
        monsterImageView.setPreserveRatio(true);

        return monsterImageView;
    }

    public char getUsingTurn() {
        return usingTurn;
    }
    public void setUsingTurn(char theChar) {
        usingTurn = theChar;
    }

    public int getRoomX() {
        return this.myRoomX;
    }

    public int getRoomY() {
        return this.myRoomY;
    }
}
