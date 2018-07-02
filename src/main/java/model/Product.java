package model;

import java.util.ArrayList;

public class Product extends Entity {

    private ArrayList<Shelf> shelvesList;
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
            shelves = Long.toString(shelf.getID());
            shelvesList.add(shelf);
        }
    }

    public String getShelvesString() {
        return shelves;
    }

    public ArrayList<Shelf> getShelvesList() {
        return shelvesList;
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
            s.setProduct(null);
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

    public double getIVA() {
        return iva;
    }

    public void setIVA(double iva) {
        this.iva = iva;
    }

    public double getPVP() {
        return pvp;
    }

    public void setPVP(double pvp) {
        this.pvp = pvp;
    }

    public String[] getDetails() {
        String[] arr = {Long.toString(getID()),
                Double.toString(getDiscount()),
                Double.toString(getIVA()),
                Double.toString(getPVP()),
                shelves};
        return arr;
    }

    public void computeShelvesString() {
        if (shelvesList.size() == 0) {
            shelves = "---";
            return;
        }
        shelves = Long.toString(shelvesList.get(0).getID());
        for (int i = 1; i < shelvesList.size(); i++) {
            shelves += ";" + Long.toString(shelvesList.get(i).getID());
        }
    }
}
