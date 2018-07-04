package model;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class Shelf extends Entity {
    int capacity;
    double dailyRent;
    long product = -1;

    public Shelf(int capacity, double dailyRent, long product) {
        this.capacity = capacity;
        this.dailyRent = dailyRent;
        this.product = product;
    }

    public Shelf(){

    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public long getProduct() {
        return product;
    }

    public void setProduct(long product) {
        this.product = product;
    }

    public double getDailyRent() {
        return dailyRent;
    }

    public void setDailyRent(double dailyRent) {
        this.dailyRent = dailyRent;
    }
    
    public String getProductString() {
    	if(product == -1) {
    		return "---";
    	} else {
    		return String.valueOf(product);
    	}
    }

    public Shelf getThis(){
        return this;
    }
}
