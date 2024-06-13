package entity.payableservice;

public class SpaService implements IPayableService {
    private double price;
    private String description;

    public SpaService() {
        this.price = 1000;
        this.description = "Spa hizmeti verilmiştir.";
    }

    public SpaService(double price, String description) {
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
        final StringBuffer sb = new StringBuffer("SpaService{");
        sb.append("price=").append(price);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
