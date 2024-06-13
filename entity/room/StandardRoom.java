package entity.room;

import java.util.Date;

public class StandardRoom extends Room {
    public StandardRoom(String roomName, int ROOM_NUMBER, int capacity, double price, boolean hasWifi, boolean hasSeaView) {
        super(roomName, ROOM_NUMBER, capacity, price);
        addFeature("TV", true);
        addFeature("Bathroom", true);
        addFeature("Towel", true);
        addFeature("Wifi", hasWifi);
        addFeature("Sea View", hasSeaView);
    }
}
