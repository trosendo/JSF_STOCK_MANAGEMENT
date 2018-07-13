package beans;

import controller.ProductService;
import model.DBProduct;
import model.DBShelf;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("createProduct")
@SessionScoped
public class ProductCreate implements Serializable {
    private DBProduct product = new DBProduct();
    @Inject
    private ProductService ps;

    public DBProduct getProduct() {
        return product;
    }

    public void save() {
        String success;
        long r = ps.storeProduct(product);
        if (r > 0) {
            success = "Produto criado com sucesso!";
        } else {
            success = "Erro!";
        }
        product = new DBProduct();
        FacesMessage msg = new FacesMessage(success, "ID associado: " + r);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
