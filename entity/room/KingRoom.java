package entity.room;

import java.util.Date;

public class KingRoom extends Room {
    public KingRoom(String roomName, int ROOM_NUMBER, int capacity, double price, boolean hasSeaView, boolean hasJacuzzi, boolean hasSafeBox, boolean hasWifi) {
        super(roomName, ROOM_NUMBER, capacity, price);
        addFeature("TV", true);
        addFeature("Bathroom", true);
        addFeature("Towel", true);
        addFeature("Sea View", hasSeaView);
        addFeature("Jacuzzi", hasJacuzzi);
        addFeature("Safe", hasSafeBox);
        addFeature("Wifi", hasWifi);
    }

    @Override
    public void addRoom() {
        System.out.println("addroom in kingroom");
    }

    @Override
    public void listRoom() {
        System.out.println("listRoom in KingRoom");
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
        System.out.println("addrezervation in kingroom ");
    }

    @Override
    public void listRezervation() {
        System.out.println("listRezervation in KingRoom");
    }

    @Override
    public void updateRezervation() {

    }

    @Override
    public void removeRezervation() {

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
