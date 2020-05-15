package business.menuitems;

import java.io.Serializable;

public class BaseProduct implements MenuItem, Serializable {
    private String name;
    private float price;

    public BaseProduct(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public float computePrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
