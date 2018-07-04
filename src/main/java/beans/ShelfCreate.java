package beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import controller.ProductService;
import controller.ShelfService;
import model.Product;
import model.Shelf;

import java.util.List;

@Named("createShelf")
@RequestScoped
public class ShelfCreate {
	String success;
	@Inject
	Shelf shelf;
	
	public Shelf getShelf() {
		return shelf;
	}

	public String getSuccess() {
		return success;
	}

	public void save() {
		long r = ShelfService.storeShelf(shelf);
        if(r != -1) {
        	success = "Prateleira criada com sucesso!";
        } else {
        	success = "Erro!";
        }
        shelf = new Shelf();
		FacesMessage msg = new FacesMessage(success, "ID associado: " + r);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public List<Product> getProducts(){
		List<Product> l = ProductService.getProducts();
		if(l == null || l.size() == 0) {
			return null;
		}
		return l;
	}
}
