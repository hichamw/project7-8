/**
 * Created by hicha on 13-7-2016.
 */
public class main {
    public static void main(String[] args){
        Ship ship = new Ship("123456","Henk","BARGE",100,20);
        System.out.println(ship.getLength());
        System.out.println(ship.getWidth());
        System.out.println(ship.getMmsi());
        System.out.println(ship.getShipName());
        System.out.println(ship.getShipType());
    }
}
