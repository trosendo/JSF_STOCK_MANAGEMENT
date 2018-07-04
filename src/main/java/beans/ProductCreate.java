package beans;


import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import controller.ProductService;
import controller.ShelfService;
import model.Product;
import model.Shelf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named("createProduct")
@RequestScoped
public class ProductCreate implements Serializable {

	String success;

	@Inject
	Product product;
	@Inject
	ProductService ps;

	public boolean isNoShelves() {
		return getShelves() == null || getShelves().isEmpty();
	}

	public Product getProduct() {
		return product;
	}

	public String getSuccess() {
		return success;
	}


	public void save() {
		long r = ps.storeProduct(product);
		if (r != -1) {
			success = "Produto criado com sucesso!";
		} else {
			success = "Erro!";
		}
		product = new Product();
		FacesMessage msg = new FacesMessage(success, "ID associado: " + r);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<Shelf> getShelves() {
		List<Shelf> l = ShelfService.getShelves(false);
		if (l == null || l.size() == 0) {
			return null;
		}
		return l;
	}
}
