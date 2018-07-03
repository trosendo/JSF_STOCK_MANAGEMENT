package beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import controller.ProductService;
import controller.ShelfService;
import model.Product;
import model.Shelf;

import java.util.List;

@Named("createShelf")
@RequestScoped
public class ShelfCreate {
	int capacity;
	double rent;
	long productID = -1;
	
	String success;
	
	Shelf shelf;
	boolean editable;
	
	public boolean isEditable() {
		return editable;
	}
	
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	public Shelf getShelf() {
		return shelf;
	}
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int discount) {
		this.capacity = discount;
	}

	public double getRent() {
		return rent;
	}

	public void setRent(double iva) {
		this.rent = iva;
	}
	
	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public String getSuccess() {
		return success;
	}

	public void save() {
		long r = ShelfService.createShelf(capacity, rent, productID);
		capacity = 0;
		rent = 0;
		productID = -1;
        if(r != -1) {
        	success = "Prateleira criada com sucesso!";
        } else {
        	success = "Erro!";
        }
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
