package com.fengk.pojo;

import java.io.Serializable;

/**
 *
 */
public class HealthOrderTest implements Serializable {
    int id;
    double price;

    @Override
    public String toString() {
        return "HealthOrderTest{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
