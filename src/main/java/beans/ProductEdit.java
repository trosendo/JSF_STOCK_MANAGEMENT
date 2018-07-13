package beans;

import controller.ShelfService;
import model.DBProduct;
import model.DBShelf;
import org.primefaces.event.FlowEvent;

import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("editProduct")
@ViewScoped
public class ProductEdit implements Serializable {
    private DBProduct product;

    private boolean skip;
    @Inject
    private ShelfService ss;

    public void addShelf(DBShelf s){
        s.setProduct(product);
        ss.updateShelf(s);
    }

    public void removeShelf(DBShelf s){
        List<DBShelf> l = product.getShelves();
        for(DBShelf temp : l){
            if(temp.equals(s)){
                s.setProduct(null);
                ss.updateShelf(s);
            }
        }
    }


    public DBProduct getProduct() {
        return product;
    }

    public void setProduct(DBProduct product) {
        this.product = product;
        System.out.println(this.product);
    }

    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }

    }
}
