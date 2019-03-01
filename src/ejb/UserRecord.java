package ejb;

import model.P4_User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

@Stateless(name="UserRecord")
public class UserRecord {
    private EntityManager em = Persistence.createEntityManagerFactory("P4_User").createEntityManager();
    public P4_User getUser(String login, String hashPassword){
        TypedQuery<P4_User> query = em.createNamedQuery("P4_User.getPassword", P4_User.class);
        query.setParameter("login",login);
        query.setParameter("password",hashPassword);
        P4_User user = query.getSingleResult();
        return user;
    }
}
