package controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ejb.Registration;
import ejb.UserRecord;
import model.P4_User;
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

@Path("/registration")
public class RegistrationController {

    @Context
    UriInfo uriInfo;

    @EJB
    private Registration registration;

    @EJB
    private UserRecord userRecord;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(String content, @Context HttpServletRequest request) throws UnsupportedEncodingException {

        P4_User user = new P4_User();
        String decoded = java.net.URLDecoder.decode(content, "UTF-8");
        String[] json  = decoded.split("=", 2);

        JsonParser jsonParser = new JsonParser();
        JsonElement elem = jsonParser.parse(json[1]);
        JsonObject obj = elem.getAsJsonObject();

        Encryptor encryptor = new Encryptor();
        String Login = obj.get("Login").toString();
        String Password = obj.get("Password").toString();
        String hashPassword = encryptor.encryptPassword(Password);

        user.setPassword(hashPassword);
        user.setLogin(Login);
        user.setSalt(encryptor.generateSalt());
        int statusCode = registration.addUser(user);
        HttpSession session = request.getSession();
        if(statusCode == 200){
            int user_id = userRecord.getUser(Login,hashPassword).getId();
            session.setAttribute("login", Login);
            session.setAttribute("Id", user_id);
            return  Response.ok().build();
        }
        else{
            return Response.status(500).build();
        }
    }
}
