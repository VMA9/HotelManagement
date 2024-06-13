package entity.room;

import entity.rezervable.IRezervable;

public interface IRoom extends IRezervable {
    void addRoom();
    void listRoom();
    void updateRoom();
    void removeRoom();
}
