package business.menuitems;

import java.io.Serializable;
import java.util.ArrayList;

public class CompositeProduct implements MenuItem, Serializable {
    private ArrayList<BaseProduct> items = new ArrayList<BaseProduct>();

    public float computePrice() {
        int total = 0;
        for (MenuItem m : items) {
            total += m.computePrice();
        }
        return total;
    }

    public boolean addMenuItem(BaseProduct m) {
        if (m != null) {
            items.add(m);
            return true;
        }
        return false;
    }

    public boolean removeMenuItem(BaseProduct m) {
        return items.remove(m);
    }

    public boolean editMenuItem(BaseProduct m, String name) {
        for (BaseProduct i : items) {
            if (i.equals(m)) {
                i.setName(name);
                return true;
            }
        }
        return false;
    }

    public boolean editMenuItem(MenuItem m, float price) {
        for (BaseProduct i : items) {
            if (i.equals(m)) {
                i.setPrice(price);
                return true;
            }
        }
        return false;
    }

    public ArrayList<BaseProduct> getItems() {
        return items;
    }

    public void setItems(ArrayList<BaseProduct> items) {
        this.items = items;
    }
}
