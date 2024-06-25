package entity.room;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class Room implements IRoom{
    protected int roomNumber;
    protected String roomName;
    protected Map<String, Boolean> features;
    protected BigDecimal price;
    protected int capacity;
    protected String roomClass;
    protected String description;
    protected boolean isActive;

    public Room(String roomName,int roomNumber, int capacity, BigDecimal price,String roomClass, String description, boolean isActive) {
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.capacity = validateCapacity(capacity);
        this.price = price;
        this.features = new HashMap<>();
        this.roomClass = roomClass;
        this.description = description;
        this.isActive = isActive;
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
        features.put("TV", true);
        features.put("Bathroom", true);
        features.put("Towel", true);
    }

    @Override
    public boolean getAvailable() {
//        isAvailable(Date startDate, Date endDate);
        return false;
    }

    public boolean isAvailable(Date startDate, Date endDate) {
        System.out.println("checking available for room");
        return false;
    }

    @Override
    public int getRoomNumber() {
        return roomNumber;
    }

    @Override
    public String getRoomName() {
        return roomName;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public String getRoomClass() {
        return roomClass;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }
}