package model;

public class Item {
    final private int myUseAmt;
    final private char myItemType;
    final private String myName;

    public Item(char theItemType, int theItemUseAmt, String theName) {
        myUseAmt = theItemUseAmt;
        myItemType = theItemType;
        myName = theName;
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
    }
    private void dealDamage(DungeonCharacter theTarget) {
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
}
