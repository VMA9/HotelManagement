package entity.rezervable;

import java.util.Date;

public class JetskiRezervation extends Rezervable {

    public JetskiRezervation(int revervationId) {
        super.rezervationId = revervationId;
    }

    @Override
    public int getRezervationId() {
        return super.getRezervationId();
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
}
