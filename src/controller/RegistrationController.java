package controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ejb.Registration;
import model.P4_User;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


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
        user.setPassword(encryptPassword(Password.toString()));
        user.setLogin(Login.toString());
        user.setSalt(generateSalt());

        registration.addUser(user);


    }
    private String generateSalt(){
        final SecureRandom random = new SecureRandom();
        final byte[] salt = new byte[256];
        random.nextBytes(salt);
        return salt.toString();
    }
    private String encryptPassword(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
