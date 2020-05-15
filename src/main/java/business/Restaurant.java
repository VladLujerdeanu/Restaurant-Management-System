package business;

import business.menuitems.BaseProduct;
import business.menuitems.CompositeProduct;
import business.menuitems.MenuItem;
import data.BillGenerator;
import gui.ChefGUI;
import gui.WaiterGUI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class manages all the restaurant functionalities.
 */
public class Restaurant implements IRestaurantProcessing, Serializable {
    public static final int nTables = 10;
    private CompositeProduct dishes;
    private Map<Order, CompositeProduct> order;

    /**
     * The Class constructor
     */
    public Restaurant() {
        this.order = new HashMap<Order, CompositeProduct>();
        dishes = new CompositeProduct();
        Order.setNTables(nTables);
    }

    /**
     * This method returns the menu items corresponding to the order given as parameter.
     *
     * @param theOrder the order for which the menu items are returned
     * @return the menu items from the order given as parameter.
     * @pre theOrder != null
     * @post @return == Collection<MenuItem> from the order
     */
    public CompositeProduct get(Order theOrder) {
        return this.order.get(theOrder);
    }

    /**
     * This method adds a items to the hash map and return the items given as parameter 'm'.
     *
     * @param o the order
     * @param m items corresponding to the order
     * @return the collection of items given as parameter 'm'
     * @pre order != null
     * @pre o != null
     * @pre m != null
     * @post @return == Collection<MenuItem> from the order
     */
    public CompositeProduct add(Order o, CompositeProduct m) {

        CompositeProduct cp = this.order.put(o, m);
        WaiterGUI.update();
        ChefGUI.update();
        return cp;
    }

    /**
     * This method returns the order hash map.
     *
     * @return the order hash map
     * @pre order != null
     * @post @return == order
     */
    public Map<Order, CompositeProduct> getOrder() {
        return order;
    }

    /**
     * This method sets the order hash map.
     *
     * @pre order != null
     */
    public void setOrder(Map<Order, CompositeProduct> order) {
        this.order = order;
    }

    /**
     * This method is a getter for 'dishes' Composite Product.
     *
     * @return MenuItem dishes
     */
    public CompositeProduct getDishes() {
        return dishes;
    }

    /**
     * This is a setter method for 'dishes' Composite Product.
     *
     * @param dishes Composite Product with all dishes
     */
    public void setDishes(CompositeProduct dishes) {
        this.dishes = dishes;
    }

    @Override
    public boolean addMenuItem(BaseProduct m) {
        return getDishes().addMenuItem(m);
    }

    @Override
    public boolean deleteMenuItem(BaseProduct m) {
        return getDishes().removeMenuItem(m);
    }

    @Override
    public boolean editMenuItem(BaseProduct m, String name) {
        return getDishes().editMenuItem(m, name);
    }

    @Override
    public boolean editMenuItem(BaseProduct m, float price) {
        return getDishes().editMenuItem(m, price);
    }

    @Override
    public CompositeProduct addOrder(Order o, CompositeProduct items) {
        return add(o, items);
    }

    @Override
    public float computePrice(Order o) {
        float total = 0f;
        ArrayList<BaseProduct> orderItems = get(o).getItems();
        for (MenuItem bp : orderItems) {
            total += bp.computePrice();
        }
        return total;
    }

    @Override
    public boolean generateBill(Order o, CompositeProduct cp) {
        new BillGenerator(this, o, cp);
        return true;
    }
}
