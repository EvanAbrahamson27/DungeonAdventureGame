package model;

import controller.DungeonAdventure;

import java.util.ArrayList;
import java.util.List;

public class Hero extends DungeonCharacter {
    private final List<Item> myInventory;

    public Hero(String theName, int theHealthPoints, int theDamageMin, int theDamageMax, int theAttackSpeed,
                     double theChanceToHit) {
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit);
        myInventory = new ArrayList<>();
    }

    public void blockAttack() {

    }
    public void useItem(String theItem, DungeonCharacter theTarget) {
        for (Item item : myInventory) {
            if (item.toString().equals(theItem)) {
                DungeonAdventure.addToLog(myName + " used a " + item + "!");
                item.itemControl(theTarget);
                myInventory.remove(item);
                return;
            }
        }
        DungeonAdventure.addToLog("No item found");
    }
    public void move(int[] thePosition) {

    }
    public void performSpecialSkill() {

    }
    public void addToInventory(Item theItem) {
        myInventory.add(theItem);
    }
}
