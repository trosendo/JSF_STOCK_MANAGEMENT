package controller;

import model.DBShelf;
import repositories.ShelfRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;


@ApplicationScoped
public class ShelfService {
    @Inject
    private ShelfRepository shelfDB;

    public long storeShelf(DBShelf s) {
        return shelfDB.storeEntity(s);
    }

    public DBShelf getShelf(long id) {
        return shelfDB.getEntity(id);
    }

    public void updateShelf(DBShelf s) {
        shelfDB.updateEntity(s);
    }

    public void removeShelf(long id) {
        shelfDB.removeEntity(id);
    }

    public List<DBShelf> getShelves() {
        List<DBShelf> list = shelfDB.getValues();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    List<DBShelf> getShelves(String[] ids) {
        return shelfDB.getEntities(ids);
    }
}
