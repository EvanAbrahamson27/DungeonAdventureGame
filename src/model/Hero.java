package model;

import java.util.ArrayList;
import java.util.List;

public class Hero extends DungeonCharacter {
    private List<Item> myInventory;

    Hero(String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
                     double theChanceToHit) {
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit);
        myInventory = new ArrayList<>();
    }

    public void blockAttack() {

    }
    public void useItem(Item theItem, DungeonCharacter theTarget) {
        for (Item item : myInventory) {
            if (item.toString().equals(theItem.toString())) {
                item.itemControl(theTarget);
                myInventory.remove(item);
                break;
            }
        }
        System.out.println("No item found");
    }
    public void move(int[] thePosition) {

    }
    public void performSpecialSkill() {

    }
    public void addToInventory(Item theItem) {
        myInventory.add(theItem);
    }
}
