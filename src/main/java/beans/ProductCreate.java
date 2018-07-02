package beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import controller.ProductService;
import controller.ShelfService;
import model.Product;
import model.Shelf;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom�s Rosendo
 *
 */
@Named("createProduct")
@RequestScoped
public class ProductCreate {

	double discount;
	double iva;
	double pvp;
	String[] shelvesID;

	String success;

	Product product;
	boolean editable;

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Product getProduct() {
		return product;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getPvp() {
		return pvp;
	}

	public void setPvp(double pvp) {
		this.pvp = pvp;
	}
	
	public String[] getShelvesID() {
		return shelvesID;
	}

	public void setShelvesID(String[] shelvesID) {
		this.shelvesID = shelvesID;
	}

	public String getSuccess() {
		return success;
	}

	public void save() {
		ArrayList<Long> arr = new ArrayList<>();
		for(String s: shelvesID) {
			arr.add(Long.parseLong(s));
		}
		long r = ProductService.createProduct(discount / 100, iva / 100, pvp, arr);
		discount = 0;
		iva = 0;
		pvp = 0;
		shelvesID = null;
		if (r != -1) {
			success = "Produto criado com sucesso!";
		} else {
			success = "Erro!";
		}
		FacesMessage msg = new FacesMessage(success, "ID associado: " + r);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<Shelf> getShelves() {
		List<Shelf> l = ShelfService.getShelves(true);
		if (l == null || l.size() == 0) {
			return null;
		}
		return l;
	}

	@ManagedProperty("#{param.product}")
	Product p;
	public List<Shelf> getShelvesFromProduct() {
		List<Shelf> l = p.getShelvesList();
		if (l == null || l.size() == 0) {
			return null;
		}
		return l;
	}
}
