package repositories;

import model.DBProduct;
import model.DBShelf;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class ProductRepository extends EntityRepository<DBProduct> {
    public ProductRepository(){
        super(DBProduct.class);
    }

}
