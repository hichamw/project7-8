import okhttp3.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hicham on 13-7-2016.
 */

public class API {
    public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private String token;
    OkHttpClient client = new OkHttpClient();

    public API() {
        post();
    }

    private void post() {
        try {
            String url = "http://backenddev.teqplay.nl/auth/login";
            String json = "{ \"username\" :\"hro6\" , \"password\" : \"t0RTfgH1\"}";
            Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(JSON, json))
                    .build();
            Response response = client.newCall(request).execute();
            token = response.body().string().substring(15,168);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONArray getJSONFromUrl(String url){
        JSONArray results = new JSONArray();
        Request urlrequest = new Request.Builder()
                .url(url)
                .header("Authorization",token)
                .build();
        try {
            Response response = client.newCall(urlrequest).execute();
            results.add(new JSONParser().parse(response.body().string()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return results;
    }
    public ArrayList<Ship> getListOfShip(String url){
        ArrayList<Ship> shipList = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();
        JSONArray shipJSON = getJSONFromUrl(url);
        JSONArray shipJSONUnpacked = (JSONArray) shipJSON.get(0);
        for (int i = 0; i<shipJSONUnpacked.size();i++){
            JSONObject object = (JSONObject) shipJSONUnpacked.get(i);
            JSONObject transponder = (JSONObject) object.get("positionOfTransponder");
            String mmsi = (String)object.get("mmsi");
            String shipName = (String)object.get("name");
            String shipType = (String)object.get("shipType");
            long length = (long) transponder.get("distanceToBow") + (long) transponder.get("distanceToStern");
            long width = (long) transponder.get("distanceToPort") + (long) transponder.get("distanceToStarboard");
            if (shipType.equals("NOT_AVAILABLE")||shipType.contains("OTHER_TYPE")||shipType.contains("UNDEFINED")){
                continue;
            } else{
                if (!types.contains(shipType)){
                    types.add(shipType);
                }
                shipList.add(new Ship(mmsi,shipName,shipType,(int)length,(int)width));
            }
        }
        for (String i:types){
            System.out.println(i);
        }
        return shipList;
    }
}
