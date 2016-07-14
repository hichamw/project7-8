import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by hicham on 13-7-2016.
 */

public class main {
    public static void main(String[] args){
        API auth = new API();
        ArrayList<Ship> shipList = auth.getListOfShip("http://backenddev.teqplay.nl/ship/details");
    }
}
