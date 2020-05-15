package mainclass;

import business.Restaurant;
import business.menuitems.BaseProduct;
import data.Serialization;
import gui.GUI;

public class MainClass {

    public static void main(String[] args) {
        final Restaurant restaurant;
        if (args.length > 0)
            restaurant = Serialization.deserialize(args[0]);
        else
            restaurant = Serialization.deserialize("restaurant.ser");

        /*restaurant.addMenuItem(new BaseProduct("Cartofi Prajiti", 10));
        restaurant.addMenuItem(new BaseProduct("Cartofi Copti", 12));
        restaurant.addMenuItem(new BaseProduct("Burger", 35));
        restaurant.addMenuItem(new BaseProduct("Cheeseburger", 39));
        restaurant.addMenuItem(new BaseProduct("Crispy Strips", 32));
        restaurant.addMenuItem(new BaseProduct("Crispy Chicken", 25));
        restaurant.addMenuItem(new BaseProduct("Ketchup", 3));
        restaurant.addMenuItem(new BaseProduct("Mustar", 3));
        restaurant.addMenuItem(new BaseProduct("Sos BBQ", 5));
        restaurant.addMenuItem(new BaseProduct("Sos Usturoi", 5));
        restaurant.addMenuItem(new BaseProduct("Coca-Cola", 8));
        restaurant.addMenuItem(new BaseProduct("Fanta", 8));
        restaurant.addMenuItem(new BaseProduct("Sprite", 8));
        restaurant.addMenuItem(new BaseProduct("Apa Plata", 8));
        restaurant.addMenuItem(new BaseProduct("Apa Minerala", 8));*/

        final GUI gui = new GUI(restaurant);

        javax.swing.SwingUtilities.invokeLater(() -> GUI.createAndShowGUI(restaurant));

    }
}
