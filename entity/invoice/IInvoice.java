package entity.invoice;

import entity.payableservice.IPayableService;
import entity.room.IRoom;

import java.math.BigDecimal;
import java.sql.Date;

public interface IInvoice {
    String getInvoiceNumber();
    int getUserId();
    BigDecimal getTotalAmount();
    Date getInvoiceDate();
    void addPayableService(IPayableService iPayableService);
    void addRoomPrice(IRoom iRoom);
    boolean isActive();
}
