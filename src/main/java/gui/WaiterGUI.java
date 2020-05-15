package gui;

import business.Order;
import business.Restaurant;
import business.menuitems.BaseProduct;
import business.menuitems.CompositeProduct;
import business.menuitems.MenuItem;
import data.BillGenerator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * This class implements the Waiter Panel for the GUI
 */
public class WaiterGUI extends Component {
    public JPanel waiterPanel;
    private static JTable ordersTable;
    private static DefaultTableModel model = new DefaultTableModel();
    private static Restaurant restaurant;

    public WaiterGUI(Restaurant restaurant){
        WaiterGUI.restaurant = restaurant;

        waiterPanel = new JPanel();

        JPanel addPanel = new JPanel();
        addPanel.setLayout(new GridLayout(0,2));
        JLabel addTitle = new JLabel("Create New Order: ");
        JLabel addTable = new JLabel("Table: ");
        JTextField addTableText = new JTextField();

        addPanel.add(addTitle);
        addPanel.add(new JLabel(""));
        addPanel.add(addTable);
        addPanel.add(addTableText);

        CompositeProduct cp = new CompositeProduct();

        if(restaurant.getDishes().getItems().size() != 0) {
            JButton[] dishes = new JButton[restaurant.getDishes().getItems().size()];
            for (int i = 0; i < restaurant.getDishes().getItems().size(); i++) {
                dishes[i] = new JButton(((restaurant.getDishes().getItems().get(i))).getName());
                addPanel.add(dishes[i]);
                int finalI = i;
                dishes[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cp.addMenuItem(restaurant.getDishes().getItems().get(finalI));
                    }
                });
            }
        } else {
            addPanel.add(new JLabel("No dishes available!"));
        }

        JButton addCreateOrder = new JButton("Create Order");
        addPanel.add(addCreateOrder);

        JPanel billPanel = new JPanel();
        billPanel.setLayout(new GridLayout(0,2));
        JLabel billTitle = new JLabel("Compute Bill: ");
        JTextField billOrderID = new JTextField("Order ID");
        JButton billSubmit = new JButton("Bill");
        billPanel.add(billTitle);
        billPanel.add(new JLabel(""));
        billPanel.add(billOrderID);
        billPanel.add(billSubmit);

        JPanel ordersPanel = new JPanel();
        ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.Y_AXIS));
        JLabel ordersTitle = new JLabel("Orders: ");
        ordersPanel.add(ordersTitle);

        model.addColumn("ID");
        model.addColumn("Table");
        model.addColumn("Order");
        getData();
        ordersTable = new JTable(model);

        ordersPanel.add(ordersTable.getTableHeader());
        ordersPanel.add(ordersTable);

        addCreateOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompositeProduct newCp = new CompositeProduct();
                for(BaseProduct bp: cp.getItems()) {
                    newCp.addMenuItem(bp);
                }
                restaurant.addOrder(new Order(new Date(), Integer.parseInt(addTableText.getText())), newCp);

                addTableText.setText("");
                cp.getItems().clear();
            }
        });

        billSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Map.Entry<Order, CompositeProduct> entry : restaurant.getOrder().entrySet()){
                    if(entry.getKey().getOrderID() == Integer.parseInt(billOrderID.getText())){
                        System.out.println(entry.getKey().getOrderID() + " " + entry.getKey().getTable());
                        restaurant.generateBill(entry.getKey(), entry.getValue());
                    }
                }
            }
        });

        waiterPanel.setLayout(new BoxLayout(waiterPanel, BoxLayout.Y_AXIS));
        waiterPanel.add(addPanel);
        waiterPanel.add(billPanel);
        waiterPanel.add(ordersPanel);
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
