package beans;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import controller.ShelfService;
import model.Shelf;

@Named("detailsShelf")
@ApplicationScoped
public class ShelfDetails {
	public List<Shelf> getShelves(){
		return ShelfService.getShelves(false);
	}
	
	public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Prateleira Editada!", String.valueOf(((Shelf) event.getObject()).getID()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edição Cancelada!", String.valueOf(((Shelf) event.getObject()).getID()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Célula Alterada", "Antes: " + oldValue + ", Depois:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void delete(long id) {
        if(ShelfService.removeShelf(id) == -1){
            FacesMessage msg = new FacesMessage("Erro a remover prateleira!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Prateleira removida com sucesso!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
