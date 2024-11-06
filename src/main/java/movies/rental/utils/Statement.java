package movies.rental.utils;

import movies.customer.entity.Customer;
import movies.rental.entity.Rental;

import java.util.List;

/**
 * @author Chris Alan Apaza Aguilar
 */

public class Statement {
    public static String generate(Customer customer, List<Rental> rentals) {
        double totalAmount = calculateTotalAmount(rentals);
        int frequentRenterPoints = calculateFrequentRenterPoints(rentals);
        return formatStatement(customer, rentals, totalAmount, frequentRenterPoints);
    }

    private static double calculateTotalAmount(List<Rental> rentals) {
        return rentals.stream()
                .mapToDouble(Rental::calculateAmount)
                .sum();
    }

    private static int calculateFrequentRenterPoints(List<Rental> rentals) {
        return rentals.stream()
                .mapToInt(Rental::calculateFrequentRenterPoints)
                .sum();
    }

    private static String formatStatement(Customer customer, List<Rental> rentals, double totalAmount, int frequentRenterPoints) {
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
