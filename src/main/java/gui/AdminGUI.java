package gui;

import business.Restaurant;
import business.menuitems.BaseProduct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class implements the Admin Panel for the GUI
 */
public class AdminGUI extends Component {
    public JPanel adminPanel;
    private Restaurant restaurant;

    public AdminGUI(Restaurant restaurant){

        this.restaurant = restaurant;

        adminPanel = new JPanel() {
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 100;
                return size;
            }
        };
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new GridLayout(0,2));
        JLabel addTitle = new JLabel("~Create New Dish~");
        JLabel addName = new JLabel("Dish name: ");
        JTextField addNameText = new JTextField();
        JLabel addPrice = new JLabel("Price:");
        JTextField addPriceText = new JTextField();
        JButton addSubmit = new JButton("Submit");
        addPanel.add(addTitle);
        addPanel.add(new JLabel(""));
        addPanel.add(addName);
        addPanel.add(addNameText);
        addPanel.add(addPrice);
        addPanel.add(addPriceText);
        addPanel.add(new JLabel(""));
        addPanel.add(addSubmit);

        JPanel editPanel = new JPanel();
        editPanel.setLayout(new GridLayout(0,2));
        JLabel editTitle = new JLabel("~Edit a Dish~");
        JLabel editName = new JLabel("Dish name: ");
        JTextField editNameText = new JTextField();
        JLabel editPrice = new JLabel("Price:");
        JTextField editPriceText = new JTextField();
        JLabel editNewName = new JLabel("Edit name: ");
        JTextField editNewNameText = new JTextField();
        JLabel editNewPrice = new JLabel("Edit price:");
        JTextField editNewPriceText = new JTextField();
        JButton editSubmit = new JButton("Submit");
        editPanel.add(editTitle);
        editPanel.add(new JLabel(""));
        editPanel.add(editName);
        editPanel.add(editNameText);
        editPanel.add(editPrice);
        editPanel.add(editPriceText);
        editPanel.add(editNewName);
        editPanel.add(editNewNameText);
        editPanel.add(editNewPrice);
        editPanel.add(editNewPriceText);
        editPanel.add(new JLabel(""));
        editPanel.add(editSubmit);

        JPanel removePanel = new JPanel();
        removePanel.setLayout(new GridLayout(0,2));
        JLabel removeTitle = new JLabel("~Delete Dish~");
        JLabel removeName = new JLabel("Dish name: ");
        JTextField removeNameText = new JTextField();
        JLabel removePrice = new JLabel("Price:");
        JTextField removePriceText = new JTextField();
        JButton removeSubmit = new JButton("Submit");
        removePanel.add(removeTitle);
        removePanel.add(new JLabel(""));
        removePanel.add(removeName);
        removePanel.add(removeNameText);
        removePanel.add(removePrice);
        removePanel.add(removePriceText);
        removePanel.add(new JLabel(""));
        removePanel.add(removeSubmit);

        addSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = addNameText.getText();
                int price = Integer.parseInt(addPriceText.getText());

                BaseProduct bp = new BaseProduct(name, price);
                restaurant.getDishes().addMenuItem(bp);

                addNameText.setText("");
                addPriceText.setText("");
            }
        });

        editSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = editNameText.getText();
                int price = Integer.parseInt(editPriceText.getText());

                String newName = editNewNameText.getText();
                String newPriceText = editNewPriceText.getText();
                int newPrice = 0;
                if(!newPriceText.equals("")) {
                    newPrice = Integer.parseInt(newPriceText);
                }

                BaseProduct bp = new BaseProduct(name, price);

                if(!newName.equals("") && newPrice != 0) {
                    restaurant.getDishes().editMenuItem(bp, newName);
                    BaseProduct nbp = new BaseProduct(newName, price);
                    restaurant.getDishes().editMenuItem(nbp, newPrice);
                } else {
                    if (!newName.equals("")) {
                        restaurant.getDishes().editMenuItem(bp, newName);
                    } else {

                        if (newPrice != 0) {
                            restaurant.getDishes().editMenuItem(bp, newPrice);
                        }
                    }
                }

                editNameText.setText("");
                editPriceText.setText("");
                editNewNameText.setText("");
                editNewPriceText.setText("");
            }
        });

        removeSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = removeNameText.getText();
                int price = Integer.parseInt(removePriceText.getText());

                BaseProduct bp = new BaseProduct(name, price);
                restaurant.getDishes().removeMenuItem(bp);

                removeNameText.setText("");
                removePriceText.setText("");
            }
        });

        adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.Y_AXIS));
        adminPanel.add(addPanel);
        adminPanel.add(editPanel);
        adminPanel.add(removePanel);
    }
}
