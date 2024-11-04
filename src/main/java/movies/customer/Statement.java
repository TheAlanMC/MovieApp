package movies.customer;

import movies.rental.Rental;

/**
 * @author Chris Alan Apaza Aguilar
 */

public class Statement {
    private final Customer customer;

    public Statement(Customer customer) {
        this.customer = customer;
    }

    public String generate() {
        double totalAmount = calculateTotalAmount();
        int frequentRenterPoints = calculateFrequentRenterPoints();
        return formatStatement(totalAmount, frequentRenterPoints);
    }

    private double calculateTotalAmount() {
        return customer.getRentals().stream()
                .mapToDouble(Rental::calculateAmount)
                .sum();
    }

    private int calculateFrequentRenterPoints() {
        return customer.getRentals().stream()
                .mapToInt(Rental::calculateFrequentRenterPoints)
                .sum();
    }

    private String formatStatement(double totalAmount, int frequentRenterPoints) {
        StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");
        for (Rental rental : customer.getRentals()) {
            result.append("\t").append(rental.getMovie().getTitle()).append("\t")
                    .append(rental.calculateAmount()).append("\n");
        }
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
    }
}
