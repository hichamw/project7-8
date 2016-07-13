/**
 * Created by hicha on 13-7-2016.
 */
public class Ship {
    private String mmsi;
    private String shipName;
    private String shipType;
    private int length;
    private int width;

    public Ship(String mmsi, String shipName, String shipType, int length, int width) {
        this.mmsi = mmsi;
        this.shipName = shipName;
        this.shipType = shipType;
        this.length = length;
        this.width = width;
    }

    public String getMmsi() {
        return mmsi;
    }

    public String getShipName() {
        return shipName;
    }

    public String getShipType() {
        return shipType;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
}
