package view;

import java.io.Serializable;

import model.DungeonMap;
import model.Hero;


public class SavedState implements Serializable {
    private final DungeonMap myDungeonMap;
    private final Hero myHero;

    public SavedState(final DungeonMap theDungeonMap, final Hero theHero) {
        myDungeonMap = theDungeonMap;
        myHero = theHero;
    }

    public DungeonMap getMyDungeonMap() {
        return myDungeonMap;
    }

    public Hero getMyHero() {
        return myHero;
    }
}
