package controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ejb.HitDataRecord;
import filter.AccessFilter;
import model.P4_HitData;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileWriter;
import java.io.IOException;

@Path("/hitdata")
public class HitRecordController {

    @EJB
    HitDataRecord hitDataRecord;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addHitData(String content) throws IOException {

        AccessFilter filter = new AccessFilter();

        P4_HitData rawHitData = new P4_HitData();
        //decode from urlencoded string
        String decoded = java.net.URLDecoder.decode(content, "UTF-8");
        String[] json  = decoded.split("=", 2);

        JsonParser jsonParser = new JsonParser();
        JsonElement elem = jsonParser.parse(json[1]);
        JsonObject obj = elem.getAsJsonObject();

        JsonElement X = obj.get("X");
        JsonElement Y = obj.get("Y");
        JsonElement R = obj.get("R");

        rawHitData.setX(Double.parseDouble(X.toString()));
        rawHitData.setY(Double.parseDouble(Y.toString()));
        rawHitData.setR(Double.parseDouble(R.toString()));

        AreaCheckerController areaCheckerController = new AreaCheckerController();
        P4_HitData fullHitData =areaCheckerController.isPointInArea(rawHitData);
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(fullHitData);
        try(FileWriter writer = new FileWriter("C:\\Users\\HP\\Desktop\\test.txt", false))
        {
            writer.write(jsonResponse);
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        hitDataRecord.addHitData(fullHitData);

        return Response.ok(jsonResponse).build();



    }
    @POST
    @Path("/read")
    public void readHitData(){

    }
}
