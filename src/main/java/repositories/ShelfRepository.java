package repositories;

import model.Shelf;

public class ShelfRepository extends EntityRepository<Shelf> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final ShelfRepository INSTANCE = new ShelfRepository();

    private ShelfRepository() {

    }

    public static ShelfRepository getInstance() {
        return INSTANCE;
    }
}
