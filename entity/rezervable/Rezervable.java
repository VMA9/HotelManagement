package entity.rezervable;

import java.util.Date;

public abstract class Rezervable implements IRezervable {
    int rezervationId;
    String customer;
    Date startDate;
    Date endDate;
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
    public boolean isAvailable(Date startDate, Date endDate) {
        return false;
    }

    public int getRezervationId() {
        return rezervationId;
    }

    public void setRezervationId(int rezervationId) {
        this.rezervationId = rezervationId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public void removeRezervation() {

    }
}
