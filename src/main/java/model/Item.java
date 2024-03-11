package model;

import controller.DungeonAdventure;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.CharacterWindow;
import view.GameWindow;

public class Item {
    final private int myUseAmt;
    final private char myItemType;
    final private String myName;

    public Item(final char theItemType, final int theItemUseAmt, final String theName) {
        myUseAmt = theItemUseAmt;
        myItemType = theItemType;
        myName = theName;
    }

    public void itemControl(final DungeonCharacter theTarget) {
        switch (myItemType) {
            case 'h' -> heal(theTarget);
            case 'd' -> dealDamage(theTarget);
            case 'v' -> giveVision();
            case 'p' -> usePillar();
            default -> System.out.println("ERROR: Invalid Item Usage");
        }
    }
    private void heal(final DungeonCharacter theTarget) {
        theTarget.heal(myUseAmt);
    }
    private void dealDamage(final DungeonCharacter theTarget) {
        theTarget.takeDamage(myUseAmt);
    }
    private void giveVision() {
        // Implement "vision" ability
    }
    public char getMyItemType() {
        return myItemType;
    }
    @Override
    public String toString() {
        return myName;
    }
    public void usePillar() {
        switch(CharacterWindow.myHero.getPillars()) {
            case 0 -> {
                CharacterWindow.myHero.setDamageRange(CharacterWindow.myHero.getDamageMin() + 30,
                        CharacterWindow.myHero.getDamageMax() + 30);
                CharacterWindow.myHero.setPillars(1);
                DungeonAdventure.addToLog("You found the Pillar of Encapsulation! +30 Damage");
            }
            case 1 -> {
                CharacterWindow.myHero.setChanceToHit(CharacterWindow.myHero.getChanceToHit() + 10);
                CharacterWindow.myHero.setPillars(2);
                DungeonAdventure.addToLog("You found the Pillar of Inheritance! +10% Chance to Hit");
            }
            case 2 -> {
                CharacterWindow.myHero.setAttackSpeed(CharacterWindow.myHero.getAttackSpeed() + 3);
                CharacterWindow.myHero.setPillars(3);
                DungeonAdventure.addToLog("You found the Pillar of Abstraction! +3 attack speed");
            }
            case 3 -> {
                DungeonAdventure.addToLog("You found the Pillar of Polymorphism! You win!");
                CharacterWindow.myHero.setIsDead(true);
                GameWindow.openGameOverWindow(false);
            }
        }
    }
    public ImageView getImage() {
        Image itemImage;
        if (myItemType == 'p') {
             itemImage = new Image("Pillar.png");
        } else {
            itemImage = new Image("HealthPotion.png");
        }
        ImageView itemImageView = new ImageView(itemImage);
        itemImageView.setFitWidth(25);
        itemImageView.setPreserveRatio(true);

        return itemImageView;
    }
}
