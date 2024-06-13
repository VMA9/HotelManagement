package entity.payableservice;

public class Catering implements IPayableService {
    private double price;
    private String description;

    public Catering() {
        this.price = 1250;
        this.description = "Oda servis hizmeti verilmiştir.";
    }

    public Catering(double price, String description) {
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
        final StringBuffer sb = new StringBuffer("Catering{");
        sb.append("price=").append(price);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
