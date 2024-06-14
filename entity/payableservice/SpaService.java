package entity.payableservice;

public class SpaService extends PayableService {

    public SpaService() {
        this.serviceName = "Spa";
        this.price = 1000;
        this.description = "Spa hizmeti verilmiştir.";
    }

    public SpaService(String serviceName, double price, String description) {
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
        final StringBuffer sb = new StringBuffer("SpaService{");
        sb.append("price=").append(price);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
