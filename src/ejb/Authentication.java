package ejb;

import model.P4_User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

@Stateless(name="Authorization")
public class Authentication {

    private EntityManager em = Persistence.createEntityManagerFactory("P4_User").createEntityManager();
    public boolean findUser(String login, String hashPassword){
        TypedQuery<P4_User> query = em.createNamedQuery("P4_User.getPassword", P4_User.class);
        query.setParameter("login",login);
        query.setParameter("password",hashPassword);
        try {
            P4_User user = query.getSingleResult();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
