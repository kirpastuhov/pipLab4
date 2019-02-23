package ejb;

import model.P4_HitData;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@Stateless(name="HitData")
public class HitDataRecord {
    private EntityManager em = Persistence.createEntityManagerFactory("P4_HitData").createEntityManager();

    public void addHitData(P4_HitData hitData){

        em.getTransaction().begin();
        em.persist(hitData);
        em.getTransaction().commit();
    }
}
