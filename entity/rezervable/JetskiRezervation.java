package entity.rezervable;

import java.util.Date;

public class JetskiRezervation extends Rezervable {

    public JetskiRezervation(int rezervationId, String customer, Date startDate, Date endDate) {
        super(rezervationId, customer, startDate, endDate);
    }

    @Override
    public void addRezervation() {
        System.out.println("Jetski Rezervation");
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
        return super.isOpen();
    }
}
