package controller;

import model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DungeonAdventureTest {
    private Hero hero;
    private Monster monster;
    private Room room;

    @Before
    public void setUp() {
        // Initialize test objects
        hero = new Hero("Test Warrior", 100, 30, 50, 5, 0.8, 20, 0, 0);
        monster = new Monster("Test Monster", 100, 20, 40, 5, 0.7, 0.2, 10, 20, 0, 0, "monster.png");
        room = new Room(null, hero, null, 0, 0, false);
    }

    @Test
    public void testHeroAttack() {
        // Test hero's attack method
        // Assert that the monster's health decreases after the hero's attack
        int monsterHealthBeforeAttack = monster.getHealthPoints();
        hero.attack(monster);
        int monsterHealthAfterAttack = monster.getHealthPoints();
        assertTrue(monsterHealthAfterAttack < monsterHealthBeforeAttack);
    }

    @Test
    public void testMonsterAttack() {
        // Test monster's attack method
        // Assert that the hero's health decreases after the monster's attack
        int heroHealthBeforeAttack = hero.getHealthPoints();
        monster.attack(hero);
        int heroHealthAfterAttack = hero.getHealthPoints();
        assertTrue(heroHealthAfterAttack < heroHealthBeforeAttack);
    }


    @Test
    public void testRoomEncounterItem() {
        // Test room's encounterItem method
        // Assert that the hero adds the item to their inventory when encountering it in the room
        Item item = new Item('h', 20, "Health Potion");
        room = new Room(null, hero, item, 0, 0, false);
        int inventorySizeBefore = hero.getInventory().size();
        room.encounterItem();
        int inventorySizeAfter = hero.getInventory().size();
        assertEquals(inventorySizeBefore + 1, inventorySizeAfter);
    }


}