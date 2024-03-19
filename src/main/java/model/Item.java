/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */
package model;

import controller.DungeonAdventure;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.CharacterWindow;
import view.GameWindow;

import java.io.Serializable;


/**
 * Represents an item that can be used within the game.
 * Items can have various effects such as healing the character, dealing damage to enemies,
 * granting special abilities, or enhancing character attributes.
 */
public class Item implements Serializable {
    final private int myUseAmt;
    final private char myItemType;
    final private String myName;

    /**
     * Constructs an Item object with the specified type, usage amount, and name.
     * @param theItemType The type of the item.
     * @param theItemUseAmt The amount of usage or effect the item provides.
     * @param theName The name of the item.
     */
    public Item(final char theItemType, final int theItemUseAmt, final String theName) {
        myUseAmt = theItemUseAmt;
        myItemType = theItemType;
        myName = theName;
    }

    /**
     * Controls the usage of the item based on its type and applies its effect on the target character.
     * @param theTarget The character on which the item's effect is applied.
     */
    public void itemControl(final DungeonCharacter theTarget) {
        switch (myItemType) {
            case 'h' -> heal(theTarget);
            case 'd' -> dealDamage(theTarget);
            case 'v' -> giveVision();
            case 'p' -> usePillar();
            default -> System.out.println("ERROR: Invalid Item Usage");
        }
    }

    /**
     * Heals the target character by the specified amount.
     * @param theTarget The character to be healed.
     */
    private void heal(final DungeonCharacter theTarget) {
        theTarget.heal(myUseAmt);
    }

    /**
     * Deals damage to the target character by the specified amount.
     * @param theTarget The character to receive damage.
     */
    private void dealDamage(final DungeonCharacter theTarget) {
        theTarget.takeDamage(myUseAmt);
    }

    /**
     * Grants a special vision ability. (Not implemented yet)
     */
    private void giveVision() {
        // Implement "vision" ability
    }

    /**
     * Retrieves the type of the item.
     * @return The type of the item.
     */
    public char getMyItemType() {
        return myItemType;
    }

    /**
     * Returns a string representation of the item (its name).
     * @return The name of the item.
     */
    @Override
    public String toString() {
        return myName;
    }

    /**
     * Uses the Pillar item, granting various bonuses to the hero based on the number of pillars collected.
     * Pillars provide enhancements such as increased damage, chance to hit, and attack speed.
     * If all pillars are collected, the hero wins the game.
     */
    public void usePillar() {
        switch(CharacterWindow.myHero.getPillars()) {
            case 0 -> {
                CharacterWindow.myHero.setDamageRange(CharacterWindow.myHero.getDamageMin() + 15,
                        CharacterWindow.myHero.getDamageMax() + 15);
                CharacterWindow.myHero.setPillars(1);
                DungeonAdventure.addToLog("You found the Pillar of Encapsulation! +15 Damage");
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
                CharacterWindow.myHero.setPillars(4);
                CharacterWindow.myHero.setIsDead(true);
                GameWindow.openGameOverWindow(false);
            }
        }
    }

    /**
     * Retrieves the image of the item for graphical representation.
     * @return An ImageView object representing the item's image.
     */
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
