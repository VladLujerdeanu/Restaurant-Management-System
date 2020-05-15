package gui;

import business.Order;
import business.Restaurant;
import business.menuitems.BaseProduct;
import business.menuitems.CompositeProduct;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

/**
 * This class implements the Chef Panel for the GUI
 */
public class ChefGUI extends Component {
    public static JPanel chefPanel;
    private static JTable ordersTable;
    private static DefaultTableModel model = new DefaultTableModel();
    private static Restaurant restaurant;

    public ChefGUI(Restaurant restaurant) {
        ChefGUI.restaurant = restaurant;

        chefPanel = new JPanel();
        chefPanel.setLayout(new BoxLayout(chefPanel, BoxLayout.Y_AXIS));
        JLabel ordersTitle = new JLabel("Orders: ");
        chefPanel.add(ordersTitle);
        model.addColumn("ID");
        model.addColumn("Table");
        model.addColumn("Order");
        getData();
        ordersTable = new JTable(model);

        chefPanel.add(ordersTable.getTableHeader());
        chefPanel.add(ordersTable);
    }

    private static void getData() {
        if(restaurant != null) {
            for (Map.Entry<Order, CompositeProduct> entry : restaurant.getOrder().entrySet()) {
                String id = "" + entry.getKey().getOrderID();
                String table = "" + entry.getKey().getTable();
                String order = "";
                for (BaseProduct m : entry.getValue().getItems()) {
                    order += m.getName() + ", ";
                }
                model.addRow(new Object[]{id, table, order});
            }
        }
    }

    public static void update() {
        for(int i = model.getRowCount() - 1; i >= 0; i--){
            model.removeRow(i);
        }
        getData();
    }
}
