package controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ejb.Registration;
import model.P4_User;
import util.Encryptor;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;


@Path("/registration")
public class RegistrationController {

    @EJB
    private Registration registration;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void getUser(String content) throws UnsupportedEncodingException {

        P4_User user = new P4_User();
        String decoded = java.net.URLDecoder.decode(content, "UTF-8");
        String[] json  = decoded.split("=", 2);

        JsonParser jsonParser = new JsonParser();
        JsonElement elem = jsonParser.parse(json[1]);
        JsonObject obj = elem.getAsJsonObject();

        JsonElement Login = obj.get("Login");
        JsonElement Password = obj.get("Password");

        Encryptor encryptor = new Encryptor();
        user.setPassword(encryptor.encryptPassword(Password.toString()));
        user.setLogin(Login.toString());
        user.setSalt(encryptor.generateSalt());

        registration.addUser(user);


    }

}
