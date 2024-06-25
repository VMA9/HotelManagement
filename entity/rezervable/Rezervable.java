package entity.rezervable;

import entity.room.IRoom;
import entity.user.IUser;

import java.math.BigDecimal;
import java.sql.Date;

public abstract class Rezervable implements IRezervable {
    protected int userId;
    protected int roomId;
    protected Date startDate;
    protected Date endDate;
    protected boolean isActive;
    protected BigDecimal totalRezervationPrice;
    protected boolean isAvailable;

    public Rezervable(int userId, int roomId, Date startDate, Date endDate, boolean isActive) {
        this.userId = userId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }

    @Override
    public int getRoomId() {
        return roomId;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public boolean isAvailable(Date startDate, Date endDate) {
        return isAvailable;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public BigDecimal totalRezervationPrice() {
        return totalRezervationPrice;
    }
}
