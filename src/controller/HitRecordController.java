package controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ejb.Authentication;
import ejb.HitDataRecord;
import model.P4_HitData;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/hitdata")
public class HitRecordController {

    @EJB
    HitDataRecord hitDataRecord;

    @EJB
    Authentication authentication;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addHitData(String content, @Context HttpServletRequest request) throws IOException {

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

        HttpSession session = request.getSession();
        AreaCheckerController areaCheckerController = new AreaCheckerController();
        P4_HitData fullHitData = areaCheckerController.isPointInArea(rawHitData,session);
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(fullHitData);
        hitDataRecord.addHitData(fullHitData);

        return Response.ok(jsonResponse).build();

    }
    @GET
    @Path("/read")
    public Response readHitData(@Context HttpServletRequest request){
        HttpSession session = request.getSession();
        Gson gson = new Gson();
        int user_id = Integer.parseInt(session.getAttribute("Id").toString());
        return Response.ok().entity(gson.toJson(hitDataRecord.getHitData(user_id))).build();
    }
}
