package entity.rezervable;

import java.util.Date;

public class RoomRezervation extends Rezervable {

    public RoomRezervation(int rezervationId, String customer, Date startDate, Date endDate) {
        super(rezervationId, customer, startDate, endDate);
    }

    @Override
    public void addRezervation() {
        super.addRezervation();
    }

    @Override
    public void listRezervation() {
        super.listRezervation();
    }

    @Override
    public void updateRezervation() {
        super.updateRezervation();
    }

    @Override
    public void removeRezervation() {
        super.removeRezervation();
    }

    @Override
    public boolean isAvailable(Date startDate, Date endDate) {
        return super.isAvailable(startDate, endDate);
    }

    @Override
    public boolean isOpen() {
        return super.isOpen();
    }
}
