package entity.room;

public class EconomyRoom extends Room {
    public EconomyRoom(short ROOM_NUMBER, byte capacity, double price) {
        super(ROOM_NUMBER, capacity, price);
        addFeature("TV",true);
        addFeature("Bathroom",true);
        addFeature("Towel",true);
    }
}
