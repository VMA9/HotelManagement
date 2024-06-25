package entity.payableservice;

import java.math.BigDecimal;

public abstract class PayableService implements IPayableService{
    protected int userId;
    protected String serviceName;
    protected BigDecimal price;
    protected String description;
    protected boolean isActive;

    public PayableService(int userId, String serviceName, BigDecimal price, String description, boolean isActive) {
        this.userId = userId;
        this.serviceName = serviceName;
        this.price = price;
        this.description = description;
        this.isActive = isActive;
    }

    @Override
    public int getUserId() {
        return userId;
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
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }
}
