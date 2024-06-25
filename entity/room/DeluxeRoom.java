package entity.room;

import java.math.BigDecimal;
import java.util.Date;

public class DeluxeRoom extends Room {
    public DeluxeRoom(String roomName, int roomNumber, int capacity, BigDecimal price, String roomClass, String description, boolean hasSeaView, boolean hasJacuzzi, boolean hasSafeBox, boolean isActive) {
        super(roomName, roomNumber, capacity, price, roomClass, description, isActive);
        addFeature("Sea View", hasSeaView);
        addFeature("Jacuzzi", hasJacuzzi);
        addFeature("Safe", hasSafeBox);
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
        return features.get("Jacuzzi");
    }

    @Override
    public boolean getHasSafeBox() {
        return features.get("Safe");
    }

    @Override
    public boolean getHasWifi() {
        return false;
    }

    @Override
    public boolean isActive() {
        return super.isActive();
    }
}
