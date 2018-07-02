package model;


public class Shelf extends Entity {
    int capacity;
    Product product;
    double dailyRent;

    public Shelf(int capacity, double dailyRent, Product product) {
        this.capacity = capacity;
        this.dailyRent = dailyRent;
        if (product != null) {
            this.product = product;
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getDailyRent() {
        return dailyRent;
    }

    public void setDailyRent(double dailyRent) {
        this.dailyRent = dailyRent;
    }
    
    public String getProductString() {
    	if(product == null) {
    		return "---";
    	} else {
    		return String.valueOf(product.getID());
    	}
    }

    public Shelf getThis(){
        return this;
    }
}
