package model;

public class Item {
    final private int myUseAmt;
    final private char myItemType;

    Item(char theItemType, int theItemUseAmt) {
        myUseAmt = theItemUseAmt;
        myItemType = theItemType;
    }

    public void itemControl(DungeonCharacter theTarget) {
        switch (myItemType) {
            case 'h' -> heal(theTarget);
            case 'd' -> dealDamage(theTarget);
            case 'v' -> giveVision();
            default -> System.out.println("ERROR: Invalid Item Usage");
        }
    }

    private void heal(DungeonCharacter theTarget) {
        theTarget.heal(myUseAmt);
        this.removeFromInventory();
    }

    private void dealDamage(DungeonCharacter theTarget) {
        theTarget.takeDamage(myUseAmt);
        this.removeFromInventory();
    }

    private void giveVision() {
        // Implement "vision" ability
    }

    private void removeFromInventory() {

    }
}
