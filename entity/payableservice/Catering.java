package entity.payableservice;

public class Catering extends PayableService {
    private String serviceName;
    private double price;
    private String description;

    public Catering() {
        this.serviceName = "Catering";
        this.price = 1250;
        this.description = "Oda servis hizmeti verilmiştir.";
    }

    public Catering(String serviceName, double price, String description) {
        this.serviceName = serviceName;
        this.price = price;
        this.description = description;
    }

    @Override
    public String getPayableServiceName() {
        return serviceName;
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
