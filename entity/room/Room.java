package entity.room;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class Room implements IRoom{
    private String roomName;
    private int roomNumber;
    private Map<String, Boolean> features;
    private double price;
    private int capacity;

    public Room(String roomName,int roomNumber, int capacity, double price) {
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.capacity = validateCapacity(capacity);
        this.price = price;
        this.features = new HashMap<>();
        addDefaultFeatures();
    }
    protected void addFeature(String feature, boolean available) {
        features.put(feature, available);
    }
    private int validateCapacity(int capacity) {
        if(capacity < 1 || capacity > 10){
            throw new IllegalArgumentException("Capacity must be between 1 and 10. ");
        }
        return capacity;
    }
    private void addDefaultFeatures() {
        features.put("TV", false);
        features.put("Bathroom", false);
        features.put("Towel", false);
    }

    @Override
    public void addRoom() {
        System.out.println("adding room");
    }

    @Override
    public void listRoom() {
        System.out.println("listing room");
    }

    @Override
    public void updateRoom() {
        System.out.println("update room");
    }

    @Override
    public void removeRoom() {
        System.out.println("remove room");
    }

    @Override
    public boolean isAvailable(Date startDate, Date endDate) {
        System.out.println("checking available for room");
        return false;
    }

    @Override
    public void addRezervation() {
        System.out.println("addRezervation in Room");
    }

    @Override
    public void listRezervation() {

    }

    @Override
    public void updateRezervation() {

    }

    @Override
    public void removeRezervation() {

    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Room{");
        sb.append("roomName='").append(roomName).append('\'');
        sb.append(", ROOM_NUMBER=").append(roomNumber);
        sb.append(", features=").append(features);
        sb.append(", price=").append(price);
        sb.append(", capacity=").append(capacity);
        sb.append('}');
        return sb.toString();
    }
}