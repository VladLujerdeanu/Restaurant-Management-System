package gui;

import business.Restaurant;
import data.Serialization;

import javax.swing.*;
import java.awt.*;

public class GUI {
    final static String TEXTADMIN = "Admin";
    final static String TEXTWAITER = "Waiter";
    final static String TEXTCHEF = "Chef";
    private final Restaurant restaurant;

    public GUI(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void addComponentToPane(Container pane) {
        JTabbedPane tabbedPane = new JTabbedPane();

        AdminGUI admin = new AdminGUI(restaurant);
        WaiterGUI waiter = new WaiterGUI(restaurant);
        ChefGUI chef = new ChefGUI(restaurant);

        tabbedPane.addTab(TEXTADMIN, admin.adminPanel);
        tabbedPane.addTab(TEXTWAITER, waiter.waiterPanel);
        tabbedPane.addTab(TEXTCHEF, chef.chefPanel);

        pane.add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     * Create the GUI and show it. For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public static void createAndShowGUI(Restaurant restaurant) {
        //Create and set up the window.
        JFrame frame = new JFrame("TabDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                Serialization.serialize(restaurant);
            }
        }));

        //Create and set up the content pane.
        GUI demo = new GUI(restaurant);
        demo.addComponentToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
