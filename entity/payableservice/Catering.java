package entity.payableservice;

import java.math.BigDecimal;

public class Catering extends PayableService {

    public Catering(int userId, String serviceName, BigDecimal price, String description, boolean isActive) {
        super(userId, serviceName, price, description, isActive);
    }

    @Override
    public int getUserId() {
        return super.getUserId();
    }

    @Override
    public String getPayableServiceName() {
        return super.getPayableServiceName();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice();
    }

    @Override
    public boolean isActive() {
        return super.isActive();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Catering{");
        sb.append("userId=").append(userId);
        sb.append(", serviceName='").append(serviceName).append('\'');
        sb.append(", price=").append(price);
        sb.append(", description='").append(description).append('\'');
        sb.append(", isActive=").append(isActive);
        sb.append('}');
        return sb.toString();
    }
}
