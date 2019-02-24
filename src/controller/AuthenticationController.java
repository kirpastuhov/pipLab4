package controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ejb.Authentication;
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
import java.io.UnsupportedEncodingException;

@Path("/sign")
public class AuthenticationController {

    @EJB
    Authentication authorization;

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
        String searchingResult = authorization.getUser(Login,hashPassword);
        if(searchingResult == "Found"){
            HttpSession session = request.getSession();
            session.setAttribute("login", Login);
            return  Response.ok().build();
        }
        return  Response.status(500).build();
    }
}

