package ejb;

import model.P4_HitData;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/*
@Stateless(name="HitData") - указываем тип и имя бина, тип у нас Stateless(не сохраняет состояние)
есть еще и Stateful, но лучше самому прочитать ибо там много и это кстати спрашивает всегда

*/
@Stateless(name="HitData")
public class HitDataRecord {
    /*
     EntityManager класс который позволяет взаимодействовать с БД
     создает соединие итд, чтобы создалось соединение нуно указать persistenceUnit в persistence.xml
     Каждый такой юнит соответвует  одной сущности  БД
     */
    private EntityManager em = Persistence.createEntityManagerFactory("P4_HitData").createEntityManager();

    public void addHitData(P4_HitData hitData){
        /*
            тут лучше почитать по подробнее, но в целом тут мы открываем соеденение,
            далее делаем все свои грязные дела в БД и закрываем соединение
            Почитай по подробнее, реально, я сам плохо понимаю
         */
        em.getTransaction().begin();
        em.persist(hitData);
        em.getTransaction().commit();
    }
    public List<P4_HitData> getHitData(int user_id){
        /*
        Тут тоже мы делаем дела в БД, только уже получаем инфу, а не запихиваем
        P4_HitData.getByLoginPassword - имя запроса, который все выполняет (о них я писал в model)
        P4_HitData.class - типа получаемых данных
        query.setParameter("user_id",user_id) - указываем параметры запроса(вот с этим я долго разбирался ибо гриб)

        query.getResultList() - возвращаем лист результатов, также можно вернуть только один резултат, если мы допусти
        запрашиваем определенного одного юзера ('это кста вот так делается query.getSingleResult())


        P.S Читай коменты в P4_user

         */
        TypedQuery<P4_HitData> query = em.createNamedQuery("P4_HitData.getByLoginPassword",P4_HitData.class);
        query.setParameter("user_id",user_id);
        List<P4_HitData> userHits = query.getResultList();
        return userHits;
    }
    public List<P4_HitData> getLatsHit(int user_id){
        TypedQuery<P4_HitData> query = em.createNamedQuery("P4_HitData.getLastHit",P4_HitData.class);
        query.setParameter("user_id",user_id);
        List<P4_HitData> userHits = query.setMaxResults(1).getResultList();
        return userHits;
    }
}
