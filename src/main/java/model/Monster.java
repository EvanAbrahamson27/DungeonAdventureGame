package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.CharacterWindow;

import java.util.Random;

public class Monster extends DungeonCharacter {
    private double myChanceToHeal;
    private int myHealMin;
    private int myHealMax;
    private char usingTurn = 'n';
    final private int myRoomX;
    final private int myRoomY;
    public Monster(final String theName, final int theHealthPoints, final int theDamageMin, final int theDamageMax,
                   final int theAttackSpeed, final double theChanceToHit, final double theChanceToHeal,
                   final int theX, final int theY) {
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit);
        this.myRoomX = theX;
        this.myRoomY = theY;
    }

    public void useTurn() {
        if (!getIsDead()) {
            while (getTurns() > 0) {
                Random movePicker = new Random();
                switch (movePicker.nextInt(3)) {
                    case 0, 1 -> {
                        if (!CharacterWindow.myHero.blockAttack()) {
                            attack(CharacterWindow.myHero);
                            usingTurn = 'a';} else setTurns(getTurns() - 1);
                        }
                    case 2 -> {heal(5);setTurns(getTurns() - 1);usingTurn = 'h';}
                }
            }
            CharacterWindow.myHero.setSkillCooldown(CharacterWindow.myHero.getSkillCooldown() - 1);
            CharacterWindow.myHero.startBattle(this);
        }
    }

    public ImageView getImage() {
        Image monsterImage = new Image("Skeleton.png");
        ImageView monsterImageView = new ImageView(monsterImage);
        monsterImageView.setFitWidth(25);
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
