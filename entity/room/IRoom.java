package entity.room;

import entity.rezervable.IRezervable;

import java.math.BigDecimal;

public interface IRoom{
    int getRoomNumber();
    String getRoomName();
    BigDecimal getPrice();
    int getCapacity();
    String getRoomClass();
    String getDescription();
    boolean getAvailable();
    boolean getHasSeaView();
    boolean getHasJacuzzi();
    boolean getHasSafeBox();
    boolean getHasWifi();
    boolean isActive();

}
