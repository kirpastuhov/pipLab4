package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

@Stateless(name="Authorization")
public class Authorization {

    private EntityManager em = Persistence.createEntityManagerFactory("P4_User").createEntityManager();
    public String getUser(String login){
        TypedQuery<String> query = em.createNamedQuery("P4_User.getByLogin", String.class);
        query.setParameter("login",login);
        try {
            String userLogin = query.getSingleResult();
            return userLogin;
        }
        catch (Exception e){
            return "Not found";
        }
    }
}
