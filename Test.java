import entity.rezervable.*;
import entity.room.*;
import entity.payableservice.*;
import entity.user.*;


public class Test {
    public static void main(String[] args) {
        RezervableTest(args);
        RoomTest(args);


    }

    public static void RoomTest(String[] args) {
        Room room = new KingRoom("King Room 1",10, 2, 100, true, true, true, true);
        System.out.println(room);
        room.addRezervation();
        room.addRoom();
        IRezervable a = new KingRoom("King Room 2",9,3,120,true,true,true,true);
        Room b = new KingRoom("King Room 3",8,3,120,true,true,true,true);
        a.addRezervation();
//        a.addRoom();
        b.addRezervation();
        b.addRoom();
        b.listRezervation();
        b.listRoom();
        System.out.println(b);
        System.out.println(a);
        System.out.println(room);
    }

    public static void RezervableTest(String[] args) {
        IRezervable n = new JetskiRezervation(0);
        IRezervable b = new RoomRezervation(0);
        n.addRezervation();
        b.addRezervation();
        n = new JetskiRezervation(0);
        System.out.println(n);
        n = new RoomRezervation(1);
        System.out.println(n);

        JetskiRezervation a = new JetskiRezervation(0);
        a.addRezervation();
        System.out.println(a);
        System.out.println("-------------\n");
        IRezervable m = new RoomRezervation(2);
        m.addRezervation();

        RoomRezervation mm = new RoomRezervation(3);
        System.out.println(mm.getRezervationId());
        System.out.println(m);
        Rezervable aaaa = new RoomRezervation(4);
        System.out.println(aaaa.getRezervationId());
        System.out.println(aaaa);
        Rezervable rezervable1 = new JetskiRezervation(111);
        Rezervable rezervable2 = new JetskiRezervation(222);
        Rezervable rezervable3 = new JetskiRezervation(333);
        System.out.println(rezervable1.getRezervationId());
        System.out.println(rezervable2.getRezervationId());
        System.out.println(rezervable3.getRezervationId());

    }
}
