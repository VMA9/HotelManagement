package entity.room;

import java.math.BigDecimal;
import java.util.Date;

public class StandardRoom extends Room {

    public StandardRoom(String roomName, int roomNumber, int capacity, BigDecimal price,String roomClass, String description, boolean hasWifi, boolean hasSeaView, boolean isActive) {
        super(roomName, roomNumber, capacity, price,roomClass, description, isActive);
        addFeature("Wifi", hasWifi);
        addFeature("Sea View", hasSeaView);
    }

    @Override
    public boolean getAvailable() {
        return super.getAvailable();
    }

    @Override
    public boolean isAvailable(Date startDate, Date endDate) {
        return super.isAvailable(startDate, endDate);
    }

    @Override
    public int getRoomNumber() {
        return super.getRoomNumber();
    }

    @Override
    public String getRoomName() {
        return super.getRoomName();
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice();
    }

    @Override
    public int getCapacity() {
        return super.getCapacity();
    }

    @Override
    public String getRoomClass() {
        return super.getRoomClass();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }
    @Override
    public boolean getHasSeaView() {
        return features.get("Sea View");
    }

    @Override
    public boolean getHasJacuzzi() {
        return false;
    }

    @Override
    public boolean getHasSafeBox() {
        return false;
    }

    @Override
    public boolean getHasWifi() {
        return features.get("Wifi");
    }

    @Override
    public boolean isActive() {
        return super.isActive();
    }
}
