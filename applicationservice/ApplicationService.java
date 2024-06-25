package applicationservice;

import dao.*;
import entity.user.IUser;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ApplicationService<T> implements IApplicationService<T> {
    IUser currentUser = null;
    private IDAO<T> dao;

    public ApplicationService(IDAO<T> dao) {
        this.dao = dao;
    }

    @Override
    public void add(T t) {
        try {
            dao.save(t);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void update(T previous, T next) {
        try {
            dao.update(previous, next);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(T t) {
        try {
            dao.inactive(t);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void active(T t) {
        try {
            dao.reactive(t);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void printGetAll() {
        try {
            dao.printGetAll();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public int getById(IUser t) throws SQLException {
        return dao.getFindId(t.getTckn(), t.getPassword());
    }

    @Override
    public BigDecimal getPrice() {
        return null;
    }

    @Override
    public Date createDateParam(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date checkInUtilDate = dateFormat.parse(date);
            java.sql.Date checkInSqlDate = new java.sql.Date(checkInUtilDate.getTime());
            return checkInSqlDate;
        } catch (Exception e) {
            System.err.println("Date error!");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public IUser getByUser(String tckn) throws SQLException {
        return dao.getByUser(tckn);
    }

    @Override
    public void login(String tckn, String password) throws SQLException {
        IUser user = getByUser(tckn);
        if (user == null) {
            System.out.println("Kullanıcı bulunamadı. Yeni giriş için enter tuşuna basınız.");
        } else if (!user.getPassword().equals(password)) {
            System.out.println("Hatalı şifre girdiniz. Yeni giriş için enter tuşuna basınız.");
        }

        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("\n----------------\n" +
                               user.getName() + " başarıyla giriş yaptı.\n" +
                               "----------------\n");
        }
    }

    @Override
    public IUser getCurrentUser() {
        return currentUser;
    }

    public int calculateAge(Date birthday) {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(birthday);

        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < birthDate.get(Calendar.MONTH)) {
            age--;
        } else if (today.get(Calendar.MONTH) == birthDate.get(Calendar.MONTH)
                   && today.get(Calendar.DAY_OF_MONTH) < birthDate.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }

        return age;
    }
    public int calculateDaysBetween(Date startDate, Date endDate) {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(startDate);
        start.setTime(endDate);

        int daysBetween = 0;
        while (start.before(end)) {
            start.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }

    public IUser logout() {
        return currentUser = null;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
    //    public void addPayableService(IPayableService repayableService) {
//        try {
//            payableServiceDAO.save(repayableService);
//            System.out.println("Ücretli hizmet başarıyla eklendi.");
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void updatePayableService(IPayableService previous, IPayableService next) {
//        try {
//            payableServiceDAO.update(previous, next);
//            System.out.println("Ücretli hizmet başarıyla güncellendi.");
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void deletePayableService(IPayableService payableService) {
//        try {
//            payableServiceDAO.inactive(payableService);
//            System.out.println("Ücretli hizmet başarıyla silindi.");
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void activatePayableService(IPayableService payableService) {
//        try {
//            payableServiceDAO.reactive(payableService);
//            System.out.println("Ücretli hizmet başarıyla aktif edildi.");
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void printPayableService() {
//        try {
//            payableServiceDAO.printGetAll();
//            System.out.println("Ücretli hizmetler başarıyla yazdırıldı.");
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void addRezervation(IRezervable rezervation) {
//        try {
//            rezervableDAO.save(rezervation);
//            System.out.println("Rezervasyon başarıyla eklendi.");
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void updateReservation(IRezervable previous, IRezervable next) {
//        try {
//            rezervableDAO.update(previous, next);
//            System.out.println("Rezervasyon başarıyla güncellendi.");
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteReservation(IRezervable rezervation) {
//        try {
//            rezervableDAO.inactive(rezervation);
//            System.out.println("Rezervasyon başarıyla silindi.");
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void activateReservation(IRezervable rezervation) {
//        try {
//            rezervableDAO.reactive(rezervation);
//            System.out.println("Rezervasyon başarıyla aktif edildi.");
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void printReservation() {
//        try {
//            rezervableDAO.printGetAll();
//            System.out.println("Rezervasyonlar başarıyla yazdırıldı.");
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void addRoom(IRoom room) {
//        try {
//            roomDAO.save(room);
//            System.out.println("Oda başarıyla eklendi.");
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void updateRoom(IRoom previous, IRoom next) {
//        try {
//            roomDAO.update(previous, next);
//            System.out.println("Oda başarıyla güncellendi.");
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteRoom(IRoom room) {
//        try {
//            roomDAO.inactive(room);
//            System.out.println("Oda başarıyla silindi.");
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void activateRoom(IRoom room) {
//        try {
//            roomDAO.reactive(room);
//            System.out.println("Oda başarıyla aktif edildi.");
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void printRooms() {
//        try {
//            roomDAO.printGetAll();
//            System.out.println("Odalar başarıyla yazdırıldı");
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void addUser(IUser user) {
//        try {
//            userDAO.save(user);
//            System.out.println("Kullanıcı başarıyla eklendi.");
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void updateUser(IUser previous, IUser next) {
//        try {
//            userDAO.update(previous, next);
//            System.out.println("Kullanıcı başarıyla güncellendi.");
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteUser(IUser user) {
//        try {
//            userDAO.inactive(user);
//            System.out.println("Kullanıcı başarıyla silindi.");
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void activateUser(IUser user) {
//        try {
//            userDAO.reactive(user);
//            System.out.println("Kullanıcı başarıyla aktif edildi.");
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void printUsers() {
//        try {
//            userDAO.printGetAll();
//            System.out.println("Kullanıcılar başarıyla yazdırıldı");
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    private static void printLoggedInMenu() {
//        System.out.println("### Menu ###");
//        System.out.println("1 : Rezervasyon yap");
//        System.out.println("2 : İşlem kayıtlarını görüntüle");
//        System.out.println("3 : Alacak/Borç Bakiye Bilgisini görüntüle");
//        System.out.println("4 : Toplam kullanıcı sayısını görüntüle");
//        System.out.println("-1 : Çıkış \n\n");
//    }
}


