package entity.payableservice;

public abstract class RoomService implements PayableService{
    private double price;
    private String description;

    public RoomService() {
        this.price = 1250;
        this.description = "Oda servis hizmeti verilmiştir.";
    }

    public RoomService(double price, String description) {
        this.price = price;
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RoomService{");
        sb.append("price=").append(price);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
