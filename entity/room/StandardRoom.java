package entity.room;

public class StandardRoom extends Room {
    public StandardRoom(short ROOM_NUMBER, byte capacity, double price, boolean hasSeaView) {
        super(ROOM_NUMBER, capacity, price);
        addFeature("TV",true);
        addFeature("Bathroom",true);
        addFeature("Towel",true);
        addFeature("Sea View", hasSeaView);
    }
}
