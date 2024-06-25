import applicationservice.ApplicationService;
import dao.*;
import dbconnection.IDBConnection;
import dbconnection.MySQLDBConnection;
import entity.invoice.EFatura;
import entity.invoice.IInvoice;
import entity.payableservice.IPayableService;
import entity.payableservice.SpaService;
import entity.rezervable.IRezervable;
import entity.rezervable.RoomRezervation;
import entity.room.IRoom;
import entity.room.KingRoom;
import entity.user.Guest;
import entity.user.IUser;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;


public class Test {
    static IDBConnection dbConnection = new MySQLDBConnection();
    static IDAO rezervableDAO = new RezervableDAO(dbConnection);
    static IDAO roomDAO = new RoomDAO(dbConnection);
    static IDAO userDAO = new UserDAO(dbConnection);
    static IDAO payableServiceDAO = new PayableServiceDAO(dbConnection);
    static IDAO invoiceDAO = new InvoiceDAO(dbConnection);
    static ApplicationService<IUser> userService = new ApplicationService<>(userDAO);
    static ApplicationService<IRoom> roomService = new ApplicationService<>(roomDAO);
    static ApplicationService<IRezervable> rezervationService = new ApplicationService<>(rezervableDAO);
    static ApplicationService<IPayableService> payableService = new ApplicationService<>(payableServiceDAO);
    static ApplicationService<IInvoice> invoiceService = new ApplicationService<>(invoiceDAO);
    static Scanner scanner = new Scanner(System.in);
    static IUser user;
    static int exitCode = 1;

    public static void main(String[] args) throws SQLException {
        System.out.println("---- HOŞGELDİNİZ ----");
        while (exitCode > 0) {
            writeMenu();
        }
        System.out.println("\n---- İYİ GÜNLER ----");
    }

    private static void writeMenu() throws SQLException {
        if (userService.isLoggedIn()) {
            int choice = 0;
            printLoggedInMenu();
            do{
                user = userService.getCurrentUser();
                int userId = userDAO.getId(user);
                try{
                    System.out.print("Seçiminiz: ");
                }catch (Exception e){
                    scanner.nextLine();
                    choice = 0;
                }
                switch (choice){
                    case 1 ->{
                        System.out.println("\n---- Rezervasyon Ekranı ----");
                        String roomId;
                        boolean validation = false;
                        do {
                            System.out.print("\nOda Id: ");
                            roomId = scanner.next();
                            if (!roomId.isEmpty() && !roomId.isBlank()) {
                                validation = true;
                            } else {
                                System.out.println("\nGeçersiz oda seçimi yaptınız.");
                            }
                        } while (!validation);
                        validation = false;

                        String startDateStr;
                        Date startDate;
                        validation = false;
                        Date today = new Date(System.currentTimeMillis());
                        do {
                            System.out.print("\nLütfen tarihleri yyyy-MM-dd formatında giriniz: ");
                            System.out.print("\nRezervasyon başlangıç tarihini: ");
                            startDateStr = scanner.next();
                            startDate = rezervationService.createDateParam(startDateStr);
                            if (startDate.after(today) || startDate.equals(today)) {
                                validation = true;
                            } else {
                                System.out.println("\nGeçersiz tarih girdiniz.");
                            }
                        } while (!validation);
                        validation = false;

                        String birthdayStr;
                        Date birthday;
                        do {
                            System.out.print("\nLütfen doğum tarihini (yyyy-MM-dd formatında) giriniz: ");
                            birthdayStr = scanner.next();
                            birthday = userService.createDateParam(birthdayStr);
                            if (userService.calculateAge(birthday) >= 18) {
                                validation = true;
                            } else {
                                System.out.print("\nKayıt olmak için 18 yaşını doldurmalısınız.");
                            }
                        } while (!validation);
                        validation = false;
                    }
                }
            }while(choice <= 1 && choice >=5);
        } else {
            int choice;
            do {
                System.out.println("---------------------");
                System.out.println("#  1 - Giriş yap");
                System.out.println("#  2 - Üye ol");
                System.out.println("# -1 - Sistemden çıkış yap");
                System.out.println("---------------------");
                System.out.print("Seçiminiz: ");

                try {
                    choice = scanner.nextInt();
                } catch (Exception e) {
                    scanner.nextLine();
                    choice = 0;
                }
                switch (choice) {
                    case 1 -> {
                        do {
                            System.out.println("\n---- Giriş Ekranı ----");
                            scanner.nextLine();
                            System.out.print("\nKimlik Numaranız: ");
                            String tckn = scanner.nextLine();
                            System.out.print("Şifreniz: ");
                            String password = scanner.nextLine();
                            userService.login(tckn, password);
                            scanner.nextLine();
                        } while (!userService.isLoggedIn());
                    }
                    case 2 ->{
                        System.out.println("\n---- Kayıt Ekranı ----");
                        String tckn;
                        boolean validation = false;
                        do {
                            System.out.print("\nKimlik numaranızı giriniz(11 rakamlı): ");
                            tckn = scanner.next();
                            if (tckn.isEmpty() && tckn.isBlank()) {
                                System.out.println("\nGeçersiz TCKN girdiniz.");
                            }
                            if(userService.getByUser(tckn) != null){
                                System.out.println("\nMevcut TCKN girdiniz.");
                            }
                            if(tckn.length() != 11){
                                System.out.println("\nLütfen 11 rakamlı TCKN giriniz.");
                            }
                            if(!tckn.isEmpty() && !tckn.isBlank() && tckn.length() == 11 && userService.getByUser(tckn) == null){
                                validation = true;
                            }
                        } while (!validation);
                        validation = false;

                        String name;
                        do {
                            System.out.print("Adınız: ");
                            name = scanner.next();
                            if (!name.isEmpty() && !name.isBlank() && name.length() >= 3) {
                                validation = true;
                            } else {
                                System.out.println("\nGeçersiz ad.");
                            }
                        } while (!validation);
                        validation = false;

                        String surname;
                        do {
                            System.out.print("Soyadınız: ");
                            surname = scanner.next();
                            if (!surname.isEmpty() && !surname.isBlank() && surname.length() >= 3) {
                                validation = true;
                            } else {
                                System.out.println("\nGeçersiz soyadı.");
                            }
                        } while (!validation);
                        validation = false;

                        String password;
                        do {
                            System.out.print("Şifreniz(En az 5, en fazla 15 karakter): ");
                            password = scanner.next();
                            if (password.length() >= 5 && password.length() <= 15) {
                                validation = true;
                            } else {
                                System.out.println("\nGeçersiz şifre girdiniz. Lütfen en az 5, en fazla 15 karakterli şifrenizi giriniz.");
                            }
                        } while (!validation);
                        validation = false;

                        String email;
                        do {
                            System.out.print("Email adresinizi giriniz: ");
                            email = scanner.next();
                            if (email.length() >= 10 && email.contains("@")) {
                                validation = true;
                            } else {
                                System.out.println("\nGeçersiz email girdiniz.");
                            }
                        } while (!validation);
                        validation = false;

                        String phone;
                        do {
                            System.out.print("Telefon numaranızı giriniz(0 olmadan): ");
                            phone = scanner.next();
                            if (phone.length() == 10) {
                                validation = true;
                            } else {
                                System.out.println("\nGeçersiz telefon numarası girdiniz.");
                            }
                        } while (!validation);
                        validation = false;

                        String birthdayStr;
                        Date birthday;
                        do {
                            System.out.print("\nLütfen doğum tarihini (yyyy-MM-dd formatında) giriniz: ");
                            birthdayStr = scanner.next();
                            birthday = userService.createDateParam(birthdayStr);
                            if (userService.calculateAge(birthday) >= 18) {
                                validation = true;
                            } else {
                                System.out.print("\nKayıt olmak için 18 yaşını doldurmalısınız.");
                            }
                        } while (!validation);
                        validation = false;

                        String address;
                        do {
                            System.out.print("Adresinizi giriniz: ");
                            address = scanner.next();
                            if (address.length() >= 3) {
                                validation = true;
                            } else {
                                System.out.println("\nGeçersiz adres girdiniz.");
                            }
                        } while (!validation);
                        validation = false;

                        System.out.print("Cinsiyetiniz: ");
                        String gender;
                        do {
                            System.out.print("Cinsiyetinizi giriniz(Erkek-Kadın): ");
                            gender = scanner.next();
                            if (gender.equals("Erkek") || gender.equals("Kadın")) {
                                validation = true;
                            } else {
                                System.out.println("\nGeçersiz cinsiyet girdiniz. Büyük küçük harf hassasiyeti vardır.");
                            }
                        } while (!validation);
                        validation = false;

                        IUser newUser = new Guest(tckn,name,surname,password,email,phone,birthday,address,gender,true);
                        userService.add(newUser);
                    }
                    case -1 -> exitCode = -1;
                    default -> System.out.println("\nHatalı tuşlama yaptınız!\n");
                }
            } while (choice != 1 && choice != 2 && choice != -1);
        }
    }
    private static void printLoggedInMenu() {
        System.out.println("### Menu ###");
        System.out.println("1 : Rezervasyon yap");
        System.out.println("2 : İşlem kayıtlarını görüntüle");
        System.out.println("3 : Alacak/Borç Bakiye Bilgisini görüntüle");
        System.out.println("4 : Toplam kullanıcı sayısını görüntüle");
        System.out.println("-1 : Çıkış \n\n");
    }
//       if(user.getName().equals("Admin")){
//
//    }

//    public static void main2(String[] args) throws SQLException {
//        ApplicationService<IUser> userService = new ApplicationService<>(userDAO) {
//        };
////        IUser newUser = new Guest("33333334", "ali", "yıldırım", "efo341", "efo341@gmail.com", "+950", "1990-10-31", "ankara", "erkek", true);
////        IUser newUser2 = new Guest("333333355", "ali", "yıldırım", "efo341", "efo341@gmail.com", "+950", "1990-10-31", "ankara", "erkek", true);
////        userService.add(newUser);
////        userService.update(newUser,newUser2);
////        userService.printGetAll();
////        userService.delete(newUser);
////        userService.active(newUser);
//
//        ApplicationService<IRoom> roomService = new ApplicationService<>(roomDAO) {
//        };
//        IRoom newRoom = new KingRoom("Yalı", 401, 10, new BigDecimal(10000), "KingRoom", "description1", true, true, true, true, true);
//        IRoom newRoom2 = new KingRoom("Köşk", 401, 10, new BigDecimal(10000), "KingRoom", "description1", true, true, true, true, true);
////        roomService.add(newRoom);
////        roomService.update(newRoom,newRoom2);
////        roomService.printGetAll();
////        roomService.delete(newRoom2);
////        roomService.active(newRoom2);
//
//        ApplicationService<IRezervable> rezervationService = new ApplicationService<>(rezervableDAO) {
//        };
//        IRezervable newRezervation = new RoomRezervation(35, 15, rezervationService.createDateParam("2024-06-25"), rezervationService.createDateParam("2024-06-30"), true);
//        IRezervable newRezervation2 = new RoomRezervation(35, 14, rezervationService.createDateParam("2024-06-25"), rezervationService.createDateParam("2024-06-30"), true);
////        IUser newUser3 = new Guest("333333355", "ali", "yıldırım", "efo341", "efo341@gmail.com", "+950", "1990-10-31", "ankara", "erkek", true);
////        rezervationService.add(newRezervation);
////        rezervationService.update(newRezervation,newRezervation2);
////        rezervationService.printGetAll();
////        rezervationService.delete(newRezervation2);
////        rezervationService.active(newRezervation2);
//
//        ApplicationService<IPayableService> payableService = new ApplicationService<>(payableServiceDAO) {
//        };
//        IPayableService newPayableService = new SpaService(35, "King Service", new BigDecimal(99999), "açıklama 2", true);
//        IPayableService newPayableService2 = new SpaService(35, "King Service44", new BigDecimal(99977), "açıklama 2", true);
////        payableService.add(newPayableService);
////        payableService.update(newPayableService,newPayableService2);
////        payableService.printGetAll();
////        payableService.delete(newPayableService2);
////        payableService.active(newPayableService2);
//
//        ApplicationService<IInvoice> invoiceService = new ApplicationService<>(invoiceDAO) {
//        };
//        IInvoice newInvoiceService = new EFatura("FTR20240009", 35, new BigDecimal(666666), invoiceService.createDateParam("2024-06-25"), true);
//        IInvoice newInvoiceService2 = new EFatura("FTR20240013", 35, new BigDecimal(666666), invoiceService.createDateParam("2024-06-25"), true);
////        invoiceService.add(newInvoiceService);
////        invoiceService.update(newInvoiceService,newInvoiceService2);
////        invoiceService.printGetAll();
////        invoiceService.delete(newInvoiceService2);
////        invoiceService.active(newInvoiceService2);
//
//    }
//    public static void main(String[] args) {
//        try {
//            IUser iUser = new Guest("3333333", "ali", "yıldırım", "efo341", "efo341@gmail.com", "+950", "1990-10-31", "ankara", "erkek", true);
//            IUser iUser2 = new Guest("32111111", "ali", "yıldırım", "efo341", "efo341@gmail.com", "+950", "1990-10-31", "ankara", "erkek", true);
//            IUser iUser3 = new Guest("3912345678", "ali", "yıldırım", "efo341", "efo341@gmail.com", "+950", "1990-10-31", "ankara", "erkek", true);
//
//            String checkInDateStr = "2019-06-23";
//            String checkInDateStr2 = "2024-02-09";
//            String checkOutDateStr = "2024-08-01";
//            String checkOutDateStr2 = "2024-09-09";
//
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            java.util.Date checkInUtilDate = dateFormat.parse(checkInDateStr);
//            java.util.Date checkInUtilDate2 = dateFormat.parse(checkInDateStr2);
//            java.util.Date checkOutUtilDate = dateFormat.parse(checkOutDateStr);
//            java.util.Date checkOutUtilDate2 = dateFormat.parse(checkOutDateStr2);
//
//            java.sql.Date checkInSqlDate = new java.sql.Date(checkInUtilDate.getTime());
//            java.sql.Date checkInSqlDate2 = new java.sql.Date(checkInUtilDate2.getTime());
//            java.sql.Date checkOutSqlDate = new java.sql.Date(checkOutUtilDate.getTime());
//            java.sql.Date checkOutSqlDate2 = new java.sql.Date(checkOutUtilDate2.getTime());
//
//
//            IInvoice iInvoice =new EFatura("FTR202400017",17,new BigDecimal(170000),checkInSqlDate,true);
//            IInvoice iInvoice2 =new EFatura("FTR202400017",17,new BigDecimal(170000),new Date(2024-06-24),true);
////            invoiceDAO.save(iInvoice);
////            invoiceDAO.update(iInvoice2,iUser3,iInvoice);
////            System.out.println(invoiceDAO.getId(iInvoice2));
//        } catch (Exception e) {
//            System.out.println("main hata");
//            e.printStackTrace();
//        }
//    }
//    public static void Payableservicemain(String[] args) {
//        try{
//            IUser iUser = new Guest("3333333", "ali", "yıldırım", "efo341", "efo341@gmail.com", "+950", "1990-10-31", "ankara", "erkek", true);
//            IUser iUser2 = new Guest("32111111", "ali", "yıldırım", "efo341", "efo341@gmail.com", "+950", "1990-10-31", "ankara", "erkek", true);
//            IPayableService iPayableService = new SpaService(userDAO.getId(iUser2),"Spa Service 3", new BigDecimal(399),"kayıt 2 yenilenme", true);
////            payableServiceDAO.save(iPayableService);
//            IPayableService iPayableService2 = new SpaService(userDAO.getId(iUser),"Spa Service 2", new BigDecimal(460),"kayıt yenilendi", true);
//            payableServiceDAO.update(iPayableService2,iUser,iPayableService);
//        }catch (Exception e){
//            e.getMessage();
//            System.out.println("main error");
//        }
//    }
//    public static void Rezervation2main(String[] args) {
//        try {
//            IUser newUser = new Guest("3912345678", "ali", "yıldırım", "efo341", "efo341@gmail.com", "+950", "1990-10-31", "ankara", "erkek", true);
////            IUser newUser2 = new Guest("1234567", "ali", "yıldırım", "efo341", "efo341@gmail.com", "+950", "1990-10-31", "ankara", "erkek", true);
//            IRoom iRoom = new KingRoom("VALO2", 401, 10, new BigDecimal(10000), "KingRoom", "description1", true, true, true, true, true);
////            userDAO.save(newUser);
////            userDAO.save(newUser2);
////            roomDAO.save(iRoom);
//            String checkInDateStr = "2024-01-03";
//            String checkInDateStr2 = "2024-02-09";
//            String checkOutDateStr = "2024-08-01";
//            String checkOutDateStr2 = "2024-09-09";
//
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            java.util.Date checkInUtilDate = dateFormat.parse(checkInDateStr);
//            java.util.Date checkInUtilDate2 = dateFormat.parse(checkInDateStr2);
//            java.util.Date checkOutUtilDate = dateFormat.parse(checkOutDateStr);
//            java.util.Date checkOutUtilDate2 = dateFormat.parse(checkOutDateStr2);
//
//            java.sql.Date checkInSqlDate = new java.sql.Date(checkInUtilDate.getTime());
//            java.sql.Date checkInSqlDate2 = new java.sql.Date(checkInUtilDate2.getTime());
//            java.sql.Date checkOutSqlDate = new java.sql.Date(checkOutUtilDate.getTime());
//            java.sql.Date checkOutSqlDate2 = new java.sql.Date(checkOutUtilDate2.getTime());
//
//            IRezervable iRezervable2 = new RoomRezervation(userDAO.getId(newUser), roomDAO.getId(iRoom), checkInSqlDate, checkOutSqlDate, true);
////            IRezervable iRezervable3 = new RoomRezervation(userDAO.getId(newUser2), roomDAO.getId(iRoom), checkInSqlDate2, checkOutSqlDate2, true);
//            IUser newUser3 = new Guest("3333333", "ali", "yıldırım", "efo341", "efo341@gmail.com", "+950", "1990-10-31", "ankara", "erkek", true);
////            userDAO.save(newUser3);
//
//            IRoom iRoom3 = new KingRoom("LOL", 401, 10, new BigDecimal(10000), "KingRoom", "description1", true, true, true, true, true);
////            roomDAO.save(iRoom3);
//            IRezervable iRezervable4 = new RoomRezervation(userDAO.getId(newUser3),roomDAO.getId(iRoom3),checkInSqlDate2,checkOutSqlDate2,true);
//            IRezervable iRezervable5 = new RoomRezervation(userDAO.getId(newUser3),roomDAO.getId(iRoom3),checkInSqlDate2,checkOutSqlDate2,false);
////            rezervableDAO.save(iRezervable4);
////            rezervableDAO.save(iRezervable2);
////            rezervableDAO.save(iRezervable3);
////
//            rezervableDAO.update(iRezervable4, newUser3, iRezervable5);
//        } catch (Exception e) {
//            e.printStackTrace();
//            e.getMessage();
//            System.out.println("hata main");
//        }
//    }
//    public static void Rezervationmain(String[] args) {
//        IDAO rezervableDAO = new RezervableDAO(dbConnection);
//        IDAO roomDAO = new RoomDAO(dbConnection);
//        IDAO userDAO = new UserDAO(dbConnection);
//
//        try {
//            IUser newUser = new Guest("3912345678","ali", "yıldırım", "efo341","@gmail.com", "+950","1990,10,31","ankara", "erkek",true);
//            IUser newUser2 = new Guest("1234567","ali", "yıldırım", "efo341","@gmail.com", "+950","1990,10,31","ankara", "erkek",true);
//            IRoom iRoom = new KingRoom("VALO2", 401, 10, new BigDecimal(10000), "KingRoom", "description1", true, true, true, true, true);
////            System.out.print("Enter check-in date (yyyy-MM-dd): ");
//            String checkInDateStr = "2024-01-03";
//            String checkInDateStr2 = "2024-01-09";
////            System.out.print("Enter check-out date (yyyy-MM-dd): ");
//            String checkOutDateStr = "2024-08-01";
//            String checkOutDateStr2 = "2024-08-09";
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            java.util.Date checkInUtilDate = dateFormat.parse(checkInDateStr);
//            java.util.Date checkInUtilDate2 = dateFormat.parse(checkInDateStr2);
//            java.util.Date checkOutUtilDate = dateFormat.parse(checkOutDateStr);
//            java.util.Date checkOutUtilDate2 = dateFormat.parse(checkOutDateStr2);
//
//            java.sql.Date checkInSqlDate = new java.sql.Date(checkInUtilDate.getTime());
//            java.sql.Date checkInSqlDate2 = new java.sql.Date(checkInUtilDate.getTime());
//            java.sql.Date checkOutSqlDate = new java.sql.Date(checkOutUtilDate.getTime());
//            java.sql.Date checkOutSqlDate2 = new java.sql.Date(checkOutUtilDate.getTime());
////            IRezervable iRezervable = new RoomRezervation(userDAO.getFindId(newUser.getTckn(),newUser.getEmail()), roomDAO.getFindId(iRoom.getRoomName(),iRoom.getRoomNumber()), checkInSqlDate,checkOutSqlDate,true);
//            IRezervable iRezervable2 = new RoomRezervation(userDAO.getId(newUser), roomDAO.getId(iRoom), java.sql.Date.valueOf("2024-01-01"),java.sql.Date.valueOf("2024-06-01"),true);
////            rezervableDAO.save(iRezervable2);
//            IRezervable iRezervable3 = new RoomRezervation(userDAO.getId(newUser2), roomDAO.getId(iRoom), java.sql.Date.valueOf("2024-06-24"),java.sql.Date.valueOf("2024-06-24"),true);
////            rezervableDAO.save(iRezervable2);
////            rezervableDAO.printGetAll();
//            rezervableDAO.update(iRezervable2, newUser,iRezervable3);
////            userDAO.save(newUser);
////            java.sql.Date sqlDate = new Date(System.currentTimeMillis());
////            System.out.println(sqlDate);
////
////            java.util.Date utilDate = Calendar.getInstance().getTime();
////            Date sqlDate2 = new Date(utilDate.getTime());
////            System.out.println(sqlDate2);
//        } catch (Exception e) {
//            System.out.println("error test rezervable");
//            e.printStackTrace();
//        }
//
//    }
//    public static void Roommain(String[] args) {
//        IDAO iRoomDAO = new RoomDAO(dbConnection);
//        try {
//            IRoom iRoom = new KingRoom("VALO", 401, 10, new BigDecimal(10000), "KingRoom" , "description1", true, true, true, true,true);
//            IRoom iRoom2 = new KingRoom("LOL3", 401, 10, new BigDecimal(10000), "KingRoom" , "description1", true, true, true, true,true);
////            iRoomDAO.save(iRoom2);
////            iRoomDAO.save(iRoom);
//
////            iRoomDAO.update(iRoom,iRoom2);
////            iRoomDAO.printGetAll();
//            System.out.println(iRoomDAO.getFindId("LOL", 401));
//        } catch (Exception e){
//            System.out.println("error test");
//            System.out.println(e.getMessage());
//        }
//    }

//    public static void Usermain(String[] args) {
////        IDBConnection dbConnection = new MySQLDBConnection();
//        UserDAO userDAO = new UserDAO(dbConnection);
//        IDAO iUserDAO = new UserDAO(dbConnection);
//
//        try {
//            IUser newUser = new Guest("3912345678","ali", "yıldırım", "efo341","@gmail.com", "+950","1990,10,31","ankara", "erkek",true);
////            iUserDAO.save(newUser);
////            IUser newUser1 = new Admin("ali","123321","@admin.com", "+999", false);
////            iUserDAO.save(newUser1);
////            iUserDAO.getAll();
//            iUserDAO.printGetAll();
////            List<IUser> users = iUserDAO.getAll();
////            for (IUser user : users) {
////                System.out.println(user.getName() + " " + user.getSurname() + " " + user.getBirthday());
////            }
////            Optional<IUser> users1 = userDAO.getByTckn("32111111");
////            for(IUser user : users1){
////                System.out.println(user.getName() + " " + user.getSurname() + " " + user.getEmail());
////            }
////            // Tckn ile arama eğer tek veri verilse böyle
////            Optional<IUser> userByTckn = userDAO.getByTckn("32111111");
////            userByTckn.ifPresentOrElse(
////                    u -> System.out.println("User found by TCKN("+ u.getTckn() +"): " + u.getName() + " "+ u.getSurname()),
////                    () -> System.out.println("No user found with the given TCKN")
////            );
////
////            // Email adresi ile arama eğer tek veri verilse böyle
////            Optional<IUser> userByEmail = userDAO.getByEmail("@gmail.com");
////            userByEmail.ifPresentOrElse(
////                    u -> System.out.println("User found by email: " + u.getName() + " " + u.getSurname()),
////                    () -> System.out.println("No user found with the given email")
////            );
////            // TCKN ile arama çok veri verilirse böyle
////            List<IUser> usersByTckn = userDAO.getByTckn("32111111");
////            if (usersByTckn.isEmpty()) {
////                System.out.println("No users found with the given TCKN");
////            } else {
////                usersByTckn.forEach(u -> System.out.println("User found by TCKN: " + u.getName()));
////            }
////
////            // Email adresi ile arama çok veri verilirse böyle
////            List<IUser> usersByEmail = userDAO.getByEmail("@gmail.com");
////            if (usersByEmail.isEmpty()) {
////                System.out.println("No users found with the given email");
////            } else {
////                usersByEmail.forEach(u -> System.out.println("User found by email: " + u.getName()));
////            }
////            //kısa yoldan bulup yazdırıyor.
////            iUserDAO.printUsersByTckn("1234567");
////            iUserDAO.printUsersByEmail("@gmail.com");
////
////            iUserDAO.userInactive("1234567", "@gmail.com");
////            iUserDAO.userReactive("1234567", "@gmail.com");
////     PK ile yapılmadığından hata veriyor.kodun içini düzelt//Düzeltildi.
////            iUserDAO.update(new Guest("11012345678","ali", "yıldırım", "efo341","@gmail.com", "+950","1990,10,31","ankara", "erkek",true));
////            iUserDAO.getFindId("012345678","@gmail.com");
////            System.out.println(iUserDAO.getById("1234567", "@gmail.com"));
////            System.out.println(newUser.getId());
////            iUserDAO.update(newUser,new Guest("99999999","ali","yıldırır","1231231","@gmail.co","+999","1999.02.02","ankara yenimahalle","erkek",true));
////            System.out.println(iUserDAO.getFindId("1234567", "@gmail.com"));
////            iUserDAO.printUserFindId("1234567", "@gmail.com");
////            iUserDAO.getById("1234567","@gmail.com");
////            System.out.println(iUserDAO.getFindId("1234567", "@gmail.com"));
////            System.out.println(iUserDAO.getCreateDate(newUser));
//        } catch (SQLException e) {
//            System.out.println("hata test");
//            e.printStackTrace();
//
//        }
//    }
}
