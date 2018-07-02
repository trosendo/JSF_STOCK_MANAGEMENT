package beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@Named
@RequestScoped
public class MenuView {
	
	private MenuModel model;
	 
    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
        
        //First submenu
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("Produtos");
        
         
        DefaultMenuItem newP = new DefaultMenuItem("Novo");
        newP.setUrl("createProduct.xhtml");
        newP.setIcon("ui-icon-disk");
        firstSubmenu.addElement(newP);
        
        DefaultMenuItem edit = new DefaultMenuItem("Editar");
        edit.setUrl("updateProduct.xhtml");
        edit.setIcon("ui-icon-pencil");
        firstSubmenu.addElement(edit);
        
        DefaultMenuItem details = new DefaultMenuItem("Consultar");
        details.setUrl("readProduct.xhtml");
        details.setIcon("ui-icon-search");
        firstSubmenu.addElement(details);
        
        DefaultMenuItem delete = new DefaultMenuItem("Remover");
        delete.setUrl("deleteProduct.xhtml");
        delete.setIcon("ui-icon-close");
        firstSubmenu.addElement(delete);
         
        model.addElement(firstSubmenu);
        
        DefaultSubMenu secondSubmenu = new DefaultSubMenu("Prateleiras");
        
        
        DefaultMenuItem newS = new DefaultMenuItem("Nova");
        newS.setUrl("createShelf.xhtml");
        newS.setIcon("ui-icon-disk");
        secondSubmenu.addElement(newS);
        
        DefaultMenuItem editS = new DefaultMenuItem("Editar");
        editS.setUrl("updateShelf.xhtml");
        editS.setIcon("ui-icon-pencil");
        secondSubmenu.addElement(editS);
        
        DefaultMenuItem detailsS = new DefaultMenuItem("Consultar");
        detailsS.setUrl("readShelf.xhtml");
        detailsS.setIcon("ui-icon-search");
        secondSubmenu.addElement(detailsS);
        
        DefaultMenuItem deleteS = new DefaultMenuItem("Remover");
        deleteS.setUrl("deleteShelf.xhtml");
        deleteS.setIcon("ui-icon-close");
        secondSubmenu.addElement(deleteS);
        
        model.addElement(secondSubmenu);
    }
    
    public MenuModel getModel() {
    	return model;
    }
}
