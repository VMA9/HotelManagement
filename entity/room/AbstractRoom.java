package entity.room;

public abstract class AbstractRoom {
    private final short ROOM_NUMBER;
    final boolean hasTV;
    private final boolean hasBathroom;
    private final boolean hasTowel;
    private final double price;

    public AbstractRoom(short ROOM_NUMBER, boolean hasTV, boolean hasBathroom, boolean hasTowel, double price) {
        this.ROOM_NUMBER = ROOM_NUMBER;
        this.hasTV = hasTV;
        this.hasBathroom = hasBathroom;
        this.hasTowel = hasTowel;
        this.price = price;
    }
    public short getRoomNumber() {
        return ROOM_NUMBER;
    }
    public boolean hasTV() {
        return hasTV;
    }
    public boolean hasBathroom() {
        return hasBathroom;
    }
    public boolean hasTowel() {
        return hasTowel;
    }
    public double getPrice() {
        return price;

    }
}
