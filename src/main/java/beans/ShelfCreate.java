package beans;

import controller.ProductService;
import controller.ShelfService;
import model.DBProduct;
import model.DBShelf;
import model.Entity;
import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("createShelf")
@SessionScoped
public class ShelfCreate implements Serializable {
    private DBShelf shelf = new DBShelf();


    @Inject
    ShelfService ss;


    public DBShelf getShelf() {
        return shelf;
    }

    public void save() {
        String success;
        long r = ss.storeShelf(shelf);
        if (r > 0) {
            success = "Prateleira criada com sucesso!";
        } else {
            success = "Erro!";
        }
        shelf = new DBShelf();
        FacesMessage msg = new FacesMessage(success, "ID associado: " + r);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
