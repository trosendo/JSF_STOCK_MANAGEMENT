package beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import controller.ShelfService;
import model.Shelf;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import controller.ProductService;
import model.Product;

@Named("detailsProduct")
@ApplicationScoped
public class ProductDetails {

    Long[] updatedShelves;

	public List<Product> getProducts(){
		return ProductService.getProducts();
	}
	
	public void onRowEdit(RowEditEvent event) {
	    Product p = (Product) event.getObject();

	    if(updatedShelves != null && updatedShelves.length > 0){
            ArrayList<Shelf> arr = new ArrayList<>();
	        for(Long id : updatedShelves){
                Shelf s = ShelfService.getShelf(id);
                if(s != null){
                    s.setProduct(p);
                    arr.add(s);
                }
            }
            p.setShelves(arr);
        }
        FacesMessage msg = new FacesMessage("Produto Editado!", String.valueOf(((Product) event.getObject()).getID()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edição Cancelada!", String.valueOf(((Product) event.getObject()).getID()));
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
        if(ProductService.removeProduct(id) == -1){
            FacesMessage msg = new FacesMessage("Erro a remover produto!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Produto removido com sucesso!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public Long[] getUpdatedShelves() {
        return updatedShelves;
    }

    public void setUpdatedShelves(Long[] updatedShelves) {
        this.updatedShelves = updatedShelves;
    }
}
