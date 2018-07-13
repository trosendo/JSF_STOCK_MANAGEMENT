package beans;

import controller.ShelfService;
import model.DBShelf;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("detailsShelf")
@RequestScoped
public class ShelfDetails implements Serializable {

    private List<DBShelf> list;

    @Inject
    ShelfService ss;

    @PostConstruct
    public void init() {
        list = getShelves();
    }

    public List<DBShelf> getShelves() {
        List<DBShelf> l = ss.getShelves();
        if (l == null || l.size() == 0) {
            return null;
        }
        return l;
    }

    public List<DBShelf> getList() {
        return list;
    }

    public void setList(List<DBShelf> list) {
        this.list = list;
    }

    public void onRowEdit(RowEditEvent event) {
        DBShelf s = (DBShelf) event.getObject();
        ss.updateShelf(s);
        FacesMessage msg = new FacesMessage("Prateleira Editada!", String.valueOf(((DBShelf) event.getObject()).getEntityID()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edição Cancelada!", String.valueOf(((DBShelf) event.getObject()).getEntityID()));
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
        ss.removeShelf(id);
        System.out.println(id);
        FacesMessage msg = new FacesMessage("Prateleira removida com sucesso!", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }
}
