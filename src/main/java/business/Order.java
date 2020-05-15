package business;

import java.io.Serializable;
import java.util.Date;

/**
 * This class creates an object that defines the details about an order
 */

public class Order implements Serializable {
    private static int nTables = 10;
    private static int id = 0;
    private int orderID;
    private Date date;
    private int table;

    /**
     * Constructor for the Order object
     *
     * @param date  order date
     * @param table order table
     */
    public Order(Date date, int table) {
        this.orderID = ++id;
        this.date = date;
        this.table = table;
    }

    @Override
    public int hashCode() {
        return orderID % nTables;
    }

    @Override
    public boolean equals(Object obj) {
        return this.orderID == ((Order) obj).getOrderID() && this.date == ((Order) obj).getDate() && this.table == ((Order) obj).getTable();
    }

    /// GETTERS AND SETTERS

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public static void setNTables(int nTables) {
        Order.nTables = nTables;
    }
}
