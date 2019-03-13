package ejb;

import model.P4_User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


@Stateless(name="Registration")
public class Registration {
    private EntityManager em = Persistence.createEntityManagerFactory("P4_User").createEntityManager();
    public int addUser(P4_User user){
        TypedQuery<String> query = em.createNamedQuery("P4_User.getByLogin", String.class);
        query.setParameter("login", user.getLogin());
        try {
            query.getSingleResult();
            return 412;
        }
        catch (Exception e){
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return 200;
        }
    }
}
