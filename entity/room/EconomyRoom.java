package entity.room;

import java.math.BigDecimal;
import java.util.Date;

public class EconomyRoom extends Room {
    public EconomyRoom(String roomName, int roomNumber, int capacity, BigDecimal price, String roomClass, String description, boolean isActive) {
        super(roomName, roomNumber, capacity, price, roomClass, description, isActive);
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
        return false;
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
        return false;
    }

    @Override
    public boolean isActive() {
        return super.isActive();
    }
}
