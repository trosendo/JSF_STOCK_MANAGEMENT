package repositories;

import model.Product;

public class ProductRepository extends EntityRepository<Product> {

	private static final long serialVersionUID = 1L;
	private static final ProductRepository INSTANCE = new ProductRepository();

    private ProductRepository() {

    }

    public static ProductRepository getInstance() {
        return INSTANCE;
    }


}
