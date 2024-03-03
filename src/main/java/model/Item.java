package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    public ImageView getImage() {
        Image itemImage = new Image("HealthPotion.png");
        ImageView itemImageView = new ImageView(itemImage);
        itemImageView.setFitWidth(50);
        itemImageView.setPreserveRatio(true);

        return itemImageView;
    }
}
