package model;

import java.util.ArrayList;
import java.util.List;

public class DungeonMap {
    private final List<Room> myRooms;

    public DungeonMap() {
        myRooms = new ArrayList<>();
        //Temp room creation
        myRooms.add(new Room(null, null, 0, 0));
        myRooms.add(new Room(null, new Item('h', 10, "Healing Potion"),
                0, -1));
        myRooms.add(new Room(new Monster("Skeleton", 50,
                5, 15, 30, 95, 50)
                , null, 0, 1));
        myRooms.add(new Room(new Monster("Skeleton", 50,
                5, 15, 30, 95, 50)
                , null, 0, 2));
        myRooms.add(new Room(null, null,
                1, 0));
        //End temp room creation
    }

    public Room getRoomAtLocation(final int theXLocation, final int theYLocation) {
        for (Room room : myRooms) {
            if (room.getXLocation() == theXLocation && room.getYLocation() == theYLocation) {
                return room;
            }
        }
        return null;
    }
}
