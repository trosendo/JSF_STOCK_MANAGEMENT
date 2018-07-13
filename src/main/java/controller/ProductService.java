package controller;

import model.DBProduct;
import model.DBShelf;
import repositories.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@ApplicationScoped
public class ProductService implements Serializable {

    @Inject
    private ProductRepository productDB;

    public long storeProduct(DBProduct p) {
        return productDB.storeEntity(p);
    }

    public DBProduct getProduct(long productID) {
        return productDB.getEntity(productID);
    }

    public DBProduct updateProduct(DBProduct p) {
        return productDB.updateEntity(p);
    }

    public void removeProduct(long id) {
        productDB.removeEntity(id);
    }

    public List<DBProduct> getProducts() {
        List<DBProduct> list = productDB.getValues();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }
}

