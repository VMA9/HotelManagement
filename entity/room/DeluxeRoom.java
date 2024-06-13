package entity.room;

import java.util.Date;

public class DeluxeRoom extends Room {

    public DeluxeRoom(String roomName, int ROOM_NUMBER, int capacity, double price,boolean hasSeaView, boolean hasJacuzzi, boolean hasSafeBox) {
        super(roomName,ROOM_NUMBER, capacity, price);
        addFeature("TV", true);
        addFeature("Bathroom", true);
        addFeature("Towel", true);
        addFeature("Sea View", hasSeaView);
        addFeature("Jacuzzi", hasJacuzzi);
        addFeature("Safe", hasSafeBox);
    }

    @Override
    public void addRoom() {

    }

    @Override
    public void listRoom() {

    }

    @Override
    public void updateRoom() {

    }

    @Override
    public void removeRoom() {

    }

    @Override
    public boolean isAvailable(Date startDate, Date endDate) {
        return false;
    }

    @Override
    public void addRezervation() {

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
}
