package model;


import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DBProduct extends model.Entity<Long> implements java.io.Serializable {

    private double discount;
    private double iva;
    private double pvp;
    private String description;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<DBShelf> shelves= new ArrayList<>();


    public DBProduct() {
    }

    public DBProduct(double discount, double iva, double pvp) {
        this.discount = discount;
        this.iva = iva;
        this.pvp = pvp;
    }

    public DBProduct(double discount, double iva, double pvp, String description) {
        this.discount = discount;
        this.iva = iva;
        this.pvp = pvp;
        this.description = description;
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getIva() {
        return this.iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getPvp() {
        return this.pvp;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DBShelf> getShelves() {
        return shelves;
    }

    public void setShelves(List<DBShelf> shelves) {
        for(DBShelf s : this.shelves){
            s.setProduct(null);
        }
        for(DBShelf s: shelves){
            s.setProduct(this);
        }
    }

    public void addShelf(DBShelf shelf){
        shelf.setProduct(this);
    }

    @PreRemove
    public void preRemove(){
        for(DBShelf s : shelves){
            s.setProduct(null);
        }
    }

    @PrePersist
    public void prePersist(){
        if(this.iva > 1){
            this.iva /= 100;
        }
        if(this.discount > 1){
            this.discount /= 100;
        }
        if(this.description == null){
            this.description = "---";
        }
    }

}
