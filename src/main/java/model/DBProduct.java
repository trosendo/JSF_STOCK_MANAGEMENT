package model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "products")
public class DBProduct extends model.Entity {


    @Column(name="Discount")
    private double discount;
    @Column(name="IVA")
    private double iva;
    @Column(name="PVP")
    private double pvp;

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
}
