package movies.rental;

import movies.customer.Customer;

import java.util.List;

/**
 * @author Chris Alan Apaza Aguilar
 */

public class Statement {
    private final List<Rental> rentals;
    private final Customer customer;

    public Statement(Customer customer, List<Rental> rentals) {
        this.customer = customer;
        this.rentals = rentals;
    }

    public String generate() {
        double totalAmount = calculateTotalAmount();
        int frequentRenterPoints = calculateFrequentRenterPoints();
        return formatStatement(totalAmount, frequentRenterPoints);
    }

    private double calculateTotalAmount() {
        return rentals.stream()
                .mapToDouble(Rental::calculateAmount)
                .sum();
    }

    private int calculateFrequentRenterPoints() {
        return rentals.stream()
                .mapToInt(Rental::calculateFrequentRenterPoints)
                .sum();
    }

    private String formatStatement(double totalAmount, int frequentRenterPoints) {
        StringBuilder result = new StringBuilder("Rental Record for ").append(customer.getName()).append("\n");
        for (Rental rental : rentals) {
            result.append("\t").append(rental.getMovie().getTitle()).append("\t")
                    .append(rental.calculateAmount()).append("\n");
        }
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
    }
}
