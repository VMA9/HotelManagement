package entity.rezervable;


import java.math.BigDecimal;
import java.sql.Date;

public class RoomRezervation extends Rezervable {

    public RoomRezervation(int user, int room, Date startDate, Date endDate, boolean isActive) {
        super(user, room, startDate, endDate, isActive);
    }

    @Override
    public int getRoomId() {
        return super.getRoomId();
    }

    @Override
    public int getUserId() {
        return super.getUserId();
    }

    @Override
    public Date getStartDate() {
        return super.getStartDate();
    }

    @Override
    public Date getEndDate() {
        return super.getEndDate();
    }

    @Override
    public boolean isAvailable(Date startDate, Date endDate) {
        return super.isAvailable(startDate, endDate);
    }

    @Override
    public boolean isActive() {
        return super.isActive();
    }

    @Override
    public BigDecimal totalRezervationPrice() {
        return super.totalRezervationPrice();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RoomRezervation{");
        sb.append("userId=").append(userId);
        sb.append(", roomId=").append(roomId);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", isActive=").append(isActive);
        sb.append('}');
        return sb.toString();
    }
}
