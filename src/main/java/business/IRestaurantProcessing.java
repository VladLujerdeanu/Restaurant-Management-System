package business;

import business.menuitems.BaseProduct;
import business.menuitems.CompositeProduct;
import business.menuitems.MenuItem;

public interface IRestaurantProcessing {
    
    boolean addMenuItem(BaseProduct m);
    boolean deleteMenuItem(BaseProduct m);
    boolean editMenuItem(BaseProduct m, String name);
    boolean editMenuItem(BaseProduct m, float price);

    CompositeProduct addOrder(Order o, CompositeProduct items);
    float computePrice(Order o);
    boolean generateBill(Order o, CompositeProduct cp);
}
