package controller;

import model.Product;
import model.Shelf;
import repositories.ShelfRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ShelfService {
    private static ShelfRepository shelfDB = ShelfRepository.getInstance();

    public static List<Shelf> getShelves(boolean emptyShelvesOnly) {
        Collection<Shelf> collection = shelfDB.getValues();
        List<Shelf> data = new ArrayList<>();
        if (collection.size() == 0) {
            return null;
        }
        for (Shelf shelf : collection) {
        	if(emptyShelvesOnly) {
        		if(shelf.getProduct() == null) {
        			data.add(shelf);
        		}
        	} else {
        		data.add(shelf);
        	}
        }
        return data;
    }

    public static long createShelf(int capacity, double rent, long productID) {
        Product p;
        p = ProductService.getProduct(productID);
        Shelf s = new Shelf(capacity, rent, p);
        if (shelfDB.storeEntity(s) != null) {
            if (p != null) {
                ProductService.updateProduct(p, s);
            }
            return s.getID();
        } else {
            return -1;
        }
    }

    public static Shelf getShelf(long id) {
        return shelfDB.getEntity(id);
    }

    static void updateShelf(Shelf s, Product p) {
        s.setProduct(p);
    }

    public static int addedShelves() {
        return shelfDB.size();
    }

    static ArrayList<Shelf> getShelves(String[] ids) {
        return shelfDB.getEntities(ids);
    }

    public static String[] getShelfDetails(long id) {
        Shelf s = shelfDB.getEntity(id);
        if (s == null) {
            return null;
        }
        return new String[]{Long.toString(s.getID()),
                Integer.toString(s.getCapacity()),
                Double.toString(s.getDailyRent()),
                (s.getProduct() == null) ? "-1" : Long.toString(s.getProduct().getID())};
    }

    public static boolean editShelf(long id, int capacity, double rent, long productID) {
        Shelf s = shelfDB.getEntity(id);
        if (s == null) {
            return false;
        }
        s.setCapacity(capacity);
        s.setDailyRent(rent);
        if (productID != -1) {
            Product p = ProductService.getProduct(productID);
            if (p != null) {
                ProductService.removeShelfFromProduct(s.getProduct(), s);
                s.setProduct(p);
                ProductService.updateProduct(p, s);
                return true;
            } else {
                System.out.println("*******PRODUTO ASSOCIADO A PRATELEIRA NÃO FOI ALTERADO*******");
                return true;
            }
        } else {
            ProductService.removeShelfFromProduct(s.getProduct(), s);
            s.setProduct(null);
            return true;
        }
    }

    public static int removeShelf(long id) {
        Shelf s = shelfDB.removeEntity(id);
        if (s == null) {
            return -1;
        }
        Product p = s.getProduct();
        if (p != null) {
            p.removeShelf(s);
        }
        return 0;
    }
}
