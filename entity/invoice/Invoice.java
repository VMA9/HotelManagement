package entity.invoice;

import entity.payableservice.IPayableService;
import entity.room.IRoom;

import java.math.BigDecimal;
import java.sql.Date;

public abstract class Invoice implements IInvoice{
    protected String invoiceNumber;
    protected int userId;
    protected BigDecimal totalAmount = BigDecimal.ZERO;
    protected Date invoiceDate;
    protected boolean isActive;

    public Invoice(String invoiceNumber, int userId, BigDecimal totalAmount, Date invoiceDate, boolean isActive) {
        this.invoiceNumber = invoiceNumber;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.invoiceDate = invoiceDate;
        this.isActive = isActive;
    }

    @Override
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    @Override
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    @Override
    public void addPayableService(IPayableService iPayableService) {
        totalAmount = totalAmount.add(iPayableService.getPrice());
    }

    @Override
    public void addRoomPrice(IRoom iRoom) {
        totalAmount = totalAmount.add(iRoom.getPrice());
    }

    @Override
    public boolean isActive() {
        return isActive;
    }
}
