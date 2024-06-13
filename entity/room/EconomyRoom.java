package entity.room;

import java.util.Date;

public class EconomyRoom extends Room {
    public EconomyRoom(String roomName, int ROOM_NUMBER, int capacity, double price) {
        super(roomName, ROOM_NUMBER, capacity, price);
        addFeature("TV",true);
        addFeature("Bathroom",true);
        addFeature("Towel",true);
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
