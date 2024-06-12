package entity.room;

public class DeluxeRoom extends Room {

    public DeluxeRoom(short ROOM_NUMBER, byte capacity, double price,boolean hasSeaView, boolean hasJacuzzi, boolean hasSafeBox) {
        super(ROOM_NUMBER, capacity, price);
        addFeature("TV", true);
        addFeature("Bathroom", true);
        addFeature("Towel", true);
        addFeature("Sea View", hasSeaView);
        addFeature("Jacuzzi", hasJacuzzi);
        addFeature("Safe", hasSafeBox);
    }
}
