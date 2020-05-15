package data;

import business.Restaurant;

import java.io.*;

public class Serialization {

    public static void serialize(Restaurant restaurant) {

        try {
            FileOutputStream fout = new FileOutputStream("restaurant.ser");
            ObjectOutputStream out = new ObjectOutputStream(fout);

            out.writeObject(restaurant);
            out.close();
            fout.close();
            System.out.println("Serialized data saved in restaurant.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Restaurant deserialize(String path) {
        Restaurant rez = null;
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            rez = (Restaurant) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
        return rez;
    }
}
