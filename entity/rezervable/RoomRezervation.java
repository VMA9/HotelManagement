package entity.rezervable;

public class RoomRezervation extends Rezervable {

    public RoomRezervation(int rezervationId) {
        super.rezervationId = rezervationId;

    }

    @Override
    public void addRezervation() {
        System.out.println("Room Rezervation");
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
    public int getRezervationId() {
        return super.getRezervationId();
    }
}
