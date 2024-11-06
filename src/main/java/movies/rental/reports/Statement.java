package movies.rental.reports;

import movies.customer.entity.Customer;
import movies.rental.entity.Rental;

import java.util.List;

/**
 * @author Chris Alan Apaza Aguilar
 */

public class Statement {
    private static final String TAB = "\t";
    private static final String NEW_LINE = "\n";

    private static final String HEADER = "Rental Record for ";
    private static final String AMOUNT_OWED_LINE = "Amount owed is ";
    private static final String FREQUENT_RENTER_POINTS_LINE = "You earned";
    private static final String FREQUENT_RENTER_POINTS_SUFFIX = " frequent renter points";

    private final Customer customer;
    private final List<Rental> rentals;

    public Statement(Customer customer, List<Rental> rentals) {
        this.customer = customer;
        this.rentals = rentals;
    }

    public String generate() {
        double totalAmount = AmountCalculator.calculateTotalAmount(rentals);
        int frequentRenterPoints = FrequentRenterPointsCalculator.calculateTotalFrequentRenterPoints(rentals);
        return formatStatement(totalAmount, frequentRenterPoints);
    }

    private String formatStatement(double totalAmount, int frequentRenterPoints) {
        StringBuilder result = new StringBuilder(HEADER).append(customer.getName()).append(NEW_LINE);
        for (Rental rental : rentals) {
            result.append(TAB).append(rental.getMovie().getTitle()).append(TAB)
                    .append(rental.calculateAmount()).append(NEW_LINE);
        }
        result.append(AMOUNT_OWED_LINE).append(totalAmount).append(NEW_LINE);
        result.append(FREQUENT_RENTER_POINTS_LINE).append(frequentRenterPoints).append(FREQUENT_RENTER_POINTS_SUFFIX);
        return result.toString();
    }
}
