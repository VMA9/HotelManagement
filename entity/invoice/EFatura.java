package entity.invoice;

import entity.payableservice.IPayableService;
import entity.room.IRoom;

import java.math.BigDecimal;
import java.sql.Date;

public class EFatura extends Invoice{
    public EFatura(String invoiceNumber, int userId, BigDecimal totalAmount, Date invoiceDate, boolean isActive) {
        super(invoiceNumber, userId, totalAmount, invoiceDate, isActive);
    }

    @Override
    public String getInvoiceNumber() {
        return super.getInvoiceNumber();
    }

    @Override
    public int getUserId() {
        return super.getUserId();
    }

    @Override
    public BigDecimal getTotalAmount() {
        return super.getTotalAmount();
    }

    @Override
    public Date getInvoiceDate() {
        return super.getInvoiceDate();
    }

    @Override
    public void addPayableService(IPayableService iPayableService) {
        super.addPayableService(iPayableService);
    }

    @Override
    public void addRoomPrice(IRoom iRoom) {
        super.addRoomPrice(iRoom);
    }

    @Override
    public boolean isActive() {
        return super.isActive();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EFatura{");
        sb.append("invoiceNumber='").append(invoiceNumber).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", invoiceDate=").append(invoiceDate);
        sb.append(", isActive=").append(isActive);
        sb.append('}');
        return sb.toString();
    }
}
