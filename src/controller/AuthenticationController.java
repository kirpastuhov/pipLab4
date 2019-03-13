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
import java.io.UnsupportedEncodingException;
import java.util.List;

//кусок пути к контроллеру, что-то типа имени,но для браузера
@Path("/sign")
public class AuthenticationController {

/*
    с помощью аннотации @EJB (не только с помощью ее реализуется DI,там оч много всего на самом деле)
     мы реализуем Dependency Injection (DI)
    таким образом нам не нужно писать что-то
    вроде Authentication authentication = new Authentication();
    Если ты не понял почему бы просто не создать экземпляр по старинке, то
    то там можно, НО это будет тупо и в некоторых сложных ситациях DI оч классная штука (например если твой EJB синглтон,
    просто так ты его не вызовешь, но зато отсосешь)
    Кароче компонентом, который мы добавили с помощью DI нам не не нужно управлять, за нас
    это делает контейнер.
 */
    @EJB
    Authentication authentication;

    @EJB
    HitDataRecord hitDataRecord;

    @EJB
    UserRecord userRecord;
/*
   @POST это мы указывает метод HTTP, что очевидно лол
   @Path("/in") - указываем путь для метода класса, таким образом чтобы получить
   доступ у методу Response класса AuthenticationController нам в пути нужно прописать
   www.pornhub.com/sign/in
   А так же, есть пути с параметрами они указывают так @Path("/in/{parameter}")
   параметры это допутим id юзера, параметров может быть много и чтоб их заюзать, метод
   дожен быть оформлен так

    @Path("/id/{userId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewPoint(@PathParam(value = "userId",String content) int userId){ ... }

    @Consumes(MediaType.APPLICATION_JSON) - то, что принимает метод (от клиента), в данном случае он принимет JSON
    @Produces(MediaType.APPLICATION_JSON) - то, что возвращает метод (обратно на клиент), тут тоже JSON

    есть много MediaType'ов text/plain, text/html итд итп


     @Context HttpServletRequest request - контекст сервлета, чтоб можно было достать сессию, редиректить итд итп
*/
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
    @POST
    @Path("/out")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signOut(String content, @Context HttpServletRequest request){
        try {
            //удаляем сессию
            // сначала ты ее, а потом она тебя, какая ирония........
            HttpSession session = request.getSession();
            session.removeAttribute("login");
            session.removeAttribute("Id");
            return Response.ok().build();
        }
        catch (Exception e){
            return Response.status(500).build();
        }
    }
}

