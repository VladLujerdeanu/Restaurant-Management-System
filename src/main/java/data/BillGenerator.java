package data;

import business.Restaurant;
import business.menuitems.BaseProduct;
import business.menuitems.CompositeProduct;
import business.menuitems.MenuItem;
import business.Order;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BillGenerator {

    public BillGenerator(Restaurant restaurant, Order o, CompositeProduct cp) {
        ArrayList<BaseProduct> orderItems = cp.getItems();
        File bill = new File("Bill no. " + o.getOrderID() + ".txt");
        try {
            bill.createNewFile();
            FileWriter writer = new FileWriter(bill);
            writer.write("Bill for order no. " + o.getOrderID() + "\n");
            writer.write("Items:\n");
            for (BaseProduct m : orderItems) {
                writer.write(m.getName() + " - " + m.getPrice() + "\n");
            }
            writer.write("Total: " + restaurant.computePrice(o));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
