package controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ejb.Authentication;
import ejb.HitDataRecord;
import ejb.UserRecord;
import model.P4_HitData;
import util.Encryptor;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Path("/sign")
public class AuthenticationController {

    @Context
    UriInfo uriInfo;

    @EJB
    Authentication authentication;

    @EJB
    HitDataRecord hitDataRecord;

    @EJB
    UserRecord userRecord;

    @POST
    @Path("/in")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signIn(String content, @Context HttpServletRequest request) throws UnsupportedEncodingException {

        String decoded = java.net.URLDecoder.decode(content, "UTF-8");
        String[] json  = decoded.split("=", 2);

        JsonParser jsonParser = new JsonParser();
        JsonElement elem = jsonParser.parse(json[1]);
        JsonObject obj = elem.getAsJsonObject();

        String Login = obj.get("Login").toString();
        String Password = obj.get("Password").toString();

        Encryptor encryptor = new Encryptor();
        String hashPassword = encryptor.encryptPassword(Password);
        boolean searchingResult = authentication.findUser(Login,hashPassword);
        HttpSession session = request.getSession();
        if(searchingResult == true){
            int user_id = userRecord.getUser(Login, hashPassword).getId();
            session.setAttribute("login", Login);
            session.setAttribute("Id", user_id);
            List<P4_HitData> userHits = hitDataRecord.getHitData(user_id);
            Gson gson = new Gson();
            return  Response.ok().entity(gson.toJson(userHits)).build();
        }
        return  Response.status(500).build();
    }

    @Path("/out")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signOut(){

        return Response.ok().build();
    }
}

