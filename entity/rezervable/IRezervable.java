package entity.rezervable;

import java.util.Date;

public interface IRezervable {
    void addRezervation();
    void listRezervation();
    void updateRezervation();
    void removeRezervation();
    boolean isAvailable(Date startDate, Date endDate);
    boolean isOpen();
}
