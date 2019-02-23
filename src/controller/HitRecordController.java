package controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ejb.HitDataRecord;
import model.P4_HitData;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;

@Path("/hitdata")
public class HitRecordController {

    @EJB
    HitDataRecord hitDataRecord;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public  void addHitData(String content) throws UnsupportedEncodingException {

        P4_HitData hitData = new P4_HitData();
        String decoded = java.net.URLDecoder.decode(content, "UTF-8");
        String[] json  = decoded.split("=", 2);

        JsonParser jsonParser = new JsonParser();
        JsonElement elem = jsonParser.parse(json[1]);
        JsonObject obj = elem.getAsJsonObject();

        JsonElement X = obj.get("X");
        JsonElement Y = obj.get("Y");
        JsonElement R = obj.get("R");

        hitData.setX(Double.parseDouble(X.toString()));
        hitData.setY(Double.parseDouble(Y.toString()));
        hitData.setR(Double.parseDouble(R.toString()));

        AreaCheckerController areaCheckerController = new AreaCheckerController();
        areaCheckerController.isPointInArea(hitData);
        hitDataRecord.addHitData(hitData);

    }
    @POST
    @Path("/read")
    public void readHitData(){

    }
}
