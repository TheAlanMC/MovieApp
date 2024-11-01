package movies;

import java.util.Vector;

public class Customer {
    private String name;
    private Vector<Rental> rentals = new Vector<>();

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Vector<Rental> getRentals() {
        return rentals;
    }

    public void addRental(Rental arg) {
        rentals.addElement(arg);
    }
}