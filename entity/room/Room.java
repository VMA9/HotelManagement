package entity.room;

import java.util.HashMap;
import java.util.Map;

public abstract class Room {
    private final short ROOM_NUMBER;
    private final Map<String, Boolean> features;
    private double price;
    private byte capacity;

    public Room(short ROOM_NUMBER, byte capacity, double price) {
        this.ROOM_NUMBER = ROOM_NUMBER;
        this.capacity = checkCapacity(capacity);
        this.price = price;
        this.features = new HashMap<>();

    }
    protected void addFeature(String feature, boolean available) {
        features.put(feature, available);
    }
    private byte checkCapacity(byte capacity) {
        if(checkBooleanCapacity()){
            this.capacity = capacity;
        }
        return capacity = -1;
    }
    private boolean checkBooleanCapacity(){
        return capacity >= 1 && capacity <= 10;
    }
    private void defaultFeature() {
        features.put("TV", false);
        features.put("Bathroom", false);
        features.put("Towel", false);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Room{");
        sb.append("ROOM_NUMBER=").append(ROOM_NUMBER);
        sb.append(", features=").append(features);
        sb.append(", price=").append(price);
        sb.append(", capacity=").append(capacity);
        sb.append('}');
        return sb.toString();
    }

}