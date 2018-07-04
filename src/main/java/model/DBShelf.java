package model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "shelves")
public class DBShelf extends model.Entity {

    @Column(name="Capacity")
    private int capacity;
    @Column(name="DailyRent")
    private double dailyRent;
    @Column(name="ProductID")
    private long product;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getDailyRent() {
        return dailyRent;
    }

    public void setDailyRent(double dailyRent) {
        this.dailyRent = dailyRent;
    }

    public long getProduct() {
        return product;
    }

    public void setProduct(long product) {
        this.product = product;
    }
}
