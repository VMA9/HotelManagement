package entity.payableservice;

import java.math.BigDecimal;

public interface IPayableService {
    int getUserId();
    String getPayableServiceName();
    String getDescription();
    BigDecimal getPrice();
    boolean isActive();
}
