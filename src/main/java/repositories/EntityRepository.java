package repositories;

import model.DBProduct;
import model.DBShelf;
import model.Entity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Transactional
public abstract class EntityRepository<T extends Entity<Long>> implements Serializable {


    @PersistenceContext
    private EntityManager em;
    private Class<T> tClass;
    private String className;

    EntityRepository() {
    }

    public EntityRepository(Class<T> tClass){
        this.tClass = tClass;
        className = tClass.getSimpleName();
    }

    public long storeEntity(T obj) {
        em.persist(obj);
        return obj.getEntityID();
    }

    public T getEntity(long id) {
        return em.find(tClass, id);
    }

    public T updateEntity(T obj) {
        return em.merge(obj);
    }

    public void removeEntity(long id) {
        T e = getEntity(id);
        em.remove(e);
    }

    public List getValues() {
        if (em == null) {
            return null;
        }
        Query query = em.createQuery("SELECT e FROM " + className + " e");
        if (query == null) {
            return null;
        }
        return query.getResultList();
    }

    public ArrayList<T> getEntities(String[] ids) {
        ArrayList<T> arr = new ArrayList<>(ids.length);
        boolean foundAtLeastOne = false;
        for (String id : ids) {
            T s = getEntity(Long.parseLong(id));
            if (s != null) {
                arr.add(s);
                foundAtLeastOne = true;
            }
        }
        return (foundAtLeastOne) ? arr : null;
    }

}
