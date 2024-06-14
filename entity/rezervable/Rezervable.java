package entity.rezervable;

import java.util.Date;

public abstract class Rezervable implements IRezervable {
    protected int rezervationId;
    protected String customer;
    protected Date startDate;
    protected Date endDate;

    public Rezervable(int rezervationId, String customer, Date startDate, Date endDate) {
        this.rezervationId = rezervationId;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
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
    @Override
    public boolean isAvailable(Date startDate, Date endDate) {
        return false;
    }

    @Override
    public boolean isOpen() {
        return false;
    }
}
