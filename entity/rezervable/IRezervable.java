package entity.rezervable;

import java.math.BigDecimal;
import java.sql.Date;

public interface IRezervable {
    int getRoomId();
    int getUserId();
    Date getStartDate();
    Date getEndDate();
    boolean isAvailable(Date startDate, Date endDate);
    boolean isActive();
    BigDecimal totalRezervationPrice();
}
