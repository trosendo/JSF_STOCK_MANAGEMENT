package model;

import javax.enterprise.context.SessionScoped;
import javax.persistence.*;
import javax.persistence.Entity;


@Entity
public class DBShelf extends model.Entity<Long> implements java.io.Serializable {

    private int capacity;
    private double dailyRent;
    @ManyToOne
    private DBProduct product;


    public DBShelf() {
    }

    public DBShelf(int capacity, double dailyRent) {
        this.capacity = capacity;
        this.dailyRent = dailyRent;
    }

    public DBShelf(int capacity, double iva, double pvp, DBProduct product) {
        this.capacity = capacity;
        this.dailyRent = dailyRent;
        this.product = product;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getDailyRent() {
        return this.dailyRent;
    }

    public void setDailyRent(double dailyRent) {
        this.dailyRent = dailyRent;
    }

    public DBProduct getProduct() {
        return this.product;
    }

    public void setProduct(DBProduct product) {
        this.product = product;
    }

     @PreRemove
    public void preRemove(){
        product.getShelves().remove(this);
    }
}
