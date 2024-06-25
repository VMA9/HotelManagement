package entity.room;

import java.math.BigDecimal;
import java.util.Date;

public class KingRoom extends Room {
    public KingRoom(String roomName, int roomNumber, int capacity, BigDecimal price,String roomClass, String description,boolean hasSeaView,boolean hasJacuzzi, boolean hasSafeBox, boolean hasWifi, boolean isActive) {
        super(roomName, roomNumber, capacity, price,roomClass, description, isActive);
        addFeature("Sea View", hasSeaView);
        addFeature("Jacuzzi", hasJacuzzi);
        addFeature("Safe", hasSafeBox);
        addFeature("Wifi", hasWifi);
    }


    public boolean isAvailable(Date startDate, Date endDate) {
        return super.isAvailable(startDate, endDate);
    }

    @Override
    public boolean getAvailable() {
        return super.getAvailable();
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
        return features.get("Wifi");
    }

    @Override
    public boolean isActive() {
        return super.isActive();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KingRoom{");
        sb.append("roomNumber=").append(roomNumber);
        sb.append(", roomName='").append(roomName).append('\'');
        sb.append(", features=").append(features);
        sb.append(", price=").append(price);
        sb.append(", capacity=").append(capacity);
        sb.append(", roomClass='").append(roomClass).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", isActive=").append(isActive);
        sb.append('}');
        return sb.toString();
    }
}
