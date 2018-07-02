package repositories;

import model.Entity;

import java.util.*;

import javax.enterprise.context.Dependent;
import java.io.Serializable;

@Dependent
public abstract class EntityRepository<T extends Entity> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Long, T> database;
    private long highestID;

    EntityRepository() {
        database = new LinkedHashMap<>();
        highestID = 0;
    }

    private long nextID() {
        highestID++;
        return highestID;
    }

    public T storeEntity(T obj) {
        obj.setID(nextID());
        database.put(obj.getID(), obj);
        return database.get(obj.getID());
    }

    public Collection<T> getValues() {
        return database.values();
    }

    public T getEntity(long id) {
        return database.get(id);
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

    public T removeEntity(long id) {
        return database.remove(id); // if no entity is found this returns null
    }

    public Set<Long> getKeys() {
        return database.keySet();
    }

    public int size() {
        return database.size();
    }

}
