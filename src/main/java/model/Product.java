package model;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class Product extends model.Entity {

    private List<Shelf>  shelvesList = new ArrayList<>();
    private double discount;
    private double iva;
    private double pvp;
    private String shelves = "---";

    public Product(double discount, double iva, double pvp, Shelf shelf) {
        shelvesList = new ArrayList<>();
        this.discount = discount;
        this.iva = iva;
        this.pvp = pvp;
        if (shelf != null) {
            shelves = Long.toString(shelf.getEntityID());
            shelvesList.add(shelf);
        }
    }


    public Product(){
    }

    public String getShelvesString() {
        return shelves;
    }

    public List<Shelf> getShelvesList() {
        return shelvesList;
    }
    public void setShelvesList(List<Shelf> shelvesList) {
        for(Shelf s : shelvesList){
            s.setProduct(this.getEntityID());
        }
    }

    public void addShelf(Shelf shelf) {
        shelvesList.add(shelf);
        computeShelvesString();
    }

    public void removeShelf(Shelf shelf) {
        shelvesList.remove(shelf);
        computeShelvesString();
    }

    public void removeShelves() {
        shelvesList.clear();
        shelves = "---";
    }

    public void setShelves(ArrayList<Shelf> shelves) {
        for (Shelf s : shelvesList) {
            s.setProduct(-1);
        }
        shelvesList.clear();
        shelvesList.addAll(shelves);
        computeShelvesString();
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

    public String[] getDetails() {
        String[] arr = {Long.toString(getEntityID()),
                Double.toString(getDiscount()),
                Double.toString(getIva()),
                Double.toString(getPvp()),
                shelves};
        return arr;
    }

    public void computeShelvesString() {
        if (shelvesList.size() == 0) {
            shelves = "---";
            return;
        }
        shelves = Long.toString(shelvesList.get(0).getEntityID());
        for (int i = 1; i < shelvesList.size(); i++) {
            shelves += ";" + Long.toString(shelvesList.get(i).getEntityID());
        }
    }


}
