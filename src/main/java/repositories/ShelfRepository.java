package repositories;

import model.DBShelf;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ShelfRepository extends EntityRepository<DBShelf> {
    public ShelfRepository(){
        super(DBShelf.class);
    }
}
