package controller;

import model.DBProduct;
import model.Product;
import model.Shelf;
import repositories.ProductRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;

@Transactional
@ApplicationScoped
public class ProductService implements Serializable {

    @PersistenceContext(unitName = "stock")
    EntityManager em;

	private static final long serialVersionUID = 1L;
	private static ProductRepository productDB = ProductRepository.getInstance();

	public long storeProduct(Product p){
	    DBProduct temp = new DBProduct();
	    temp.setDiscount(p.getDiscount() / 100);
	    temp.setIva(p.getIva() / 100);
	    temp.setPvp(p.getPvp());
	    em.persist(temp); // associar à table correta!!! TODO
	    return (productDB.storeEntity(p) != null) ? temp.getEntityID() : -1;
    }

    public static List<Product> getProducts() {
        Collection<Product> c = productDB.getValues();
        List<Product> data = new ArrayList<>();
        if (c.size() == 0) {
            return null;
        }
        for (Product p : c) {
            data.add(p);
        }
        return data;
    }
    
    public static long createProduct(double discount, double iva, double pvp, ArrayList<Long> shelves) {
        Product p = new Product(discount, iva, pvp, null);
        if (productDB.storeEntity(p) != null) {
        	for(Long id: shelves) {
        		Shelf s = ShelfService.getShelf(id);
        		if(s != null) {
        			s.setProduct(p.getEntityID());
        		}
        		p.addShelf(s);
        	}
            return p.getEntityID();
        } else {
            return -1;
        }
    }

    public static long createProduct(double discount, double iva, double pvp, long shelfID) {
        Shelf s;
        s = ShelfService.getShelf(shelfID);
        Product p = new Product(discount, iva, pvp, s);
        if (productDB.storeEntity(p) != null) {
            if (s != null) {
                ShelfService.updateShelf(s, p);
            }
            return p.getEntityID();
        } else {
            return -1;
        }
    }

    public static String[] getProductDetails(long id) {
        Product p = productDB.getEntity(id);
        if (p == null) {
            return null;
        }
        return new String[]{Long.toString(p.getEntityID()),
                Double.toString(p.getDiscount()),
                Double.toString(p.getIva()),
                Double.toString(p.getPvp()),
                p.getShelvesString()};
    }

    public static boolean editProduct(long id, int discount, double iva, double pvp, String[] newShelves) {
        Product p = productDB.getEntity(id);
        if (p == null) {
            return false;
        }
        p.setDiscount(discount);
        p.setIva(iva);
        p.setPvp(pvp);
        ArrayList<Shelf> shelves;
        if (newShelves != null) {
            shelves = ShelfService.getShelves(newShelves);
            if (shelves == null) {
                System.out.println("As prateleiras associadas ao produto não foram alteradas!");
            } else {
                for (Shelf s : shelves) {
                    Product temp = productDB.getEntity(s.getProduct());
                    if (temp != null) {
                        temp.removeShelf(s);
                    }
                    ShelfService.updateShelf(s, p);
                }
                p.setShelves(shelves);
            }
        } else {
            List<Shelf> arrList = p.getShelvesList();
            for (Shelf s : arrList) {
                s.setProduct(-1);
            }
            p.removeShelves();
        }
        return p.getDiscount() == discount && p.getIva() == iva && p.getPvp() == pvp;
    }

    public static Product getProduct(long productID) {
        return productDB.getEntity(productID);
    }

    static void updateProduct(Product p, Shelf s) {
        p.addShelf(s);
    }

    public static int addedProducts() {
        return productDB.size();
    }

    static void removeShelfFromProduct(Product p, Shelf s) {
        if (p != null) {
            p.removeShelf(s);
        }
    }

    public static int removeProduct(long id) {
        Product p = productDB.removeEntity(id);
        if (p == null) {
            return -1;
        }
        List<Shelf> shelvesList = p.getShelvesList();
        if (shelvesList != null) {
            for (Shelf s : shelvesList) {
                s.setProduct(-1);
            }
        }
        return 0;
    }
}

