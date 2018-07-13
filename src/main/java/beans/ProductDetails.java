package beans;

import controller.ProductService;
import model.DBProduct;
import model.DBShelf;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("detailsProduct")
@SessionScoped
public class ProductDetails implements Serializable {


    private List<DBProduct> list;
    private boolean associate = false;

    @Inject
    ProductService ps;

    private List<DBShelf> shelves;

    @PostConstruct
    public void init() {
        list = getProducts();
    }

    public List<DBProduct> getList() {
        return list;
    }

    public void setList(List<DBProduct> list) {
        this.list = list;
    }


    public List<DBProduct> getProducts() {
        return ps.getProducts();
    }

    public void onRowEdit(RowEditEvent event) {
        DBProduct p = (DBProduct) event.getObject();
        p = ps.updateProduct(p);
        p.setShelves(shelves);
        ps.updateProduct(p);
        FacesMessage msg = new FacesMessage("Produto Editado!", String.valueOf(((DBProduct) event.getObject()).getEntityID()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edição Cancelada!", String.valueOf(((DBProduct) event.getObject()).getEntityID()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Célula Alterada", "Antes: " + oldValue + ", Depois:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void delete(long id) {
        ps.removeProduct(id);
        FacesMessage msg = new FacesMessage("Produto removido com sucesso!", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public List<DBShelf> getShelves() {
        return shelves;
    }

    public void setShelves(List<DBShelf> shelves) {
        this.shelves = shelves;
    }

    public boolean getAssociate() {
        return associate;
    }

    public void setAssociate(boolean associate) {
        this.associate = associate;
    }
}
