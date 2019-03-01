package ejb;

import model.P4_HitData;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless(name="HitData")
public class HitDataRecord {
    private EntityManager em = Persistence.createEntityManagerFactory("P4_HitData").createEntityManager();

    public void addHitData(P4_HitData hitData){

        em.getTransaction().begin();
        em.persist(hitData);
        em.getTransaction().commit();
    }
    public List<P4_HitData> getHitData(int user_id){

        TypedQuery<P4_HitData> query = em.createNamedQuery("P4_HitData.getByLoginPassword",P4_HitData.class);
        query.setParameter("user_id",user_id);
        List<P4_HitData> userHits = query.getResultList();
        return userHits;
    }
}
