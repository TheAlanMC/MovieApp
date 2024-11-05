package movies.rental.strategy;

/**
 * @author Chris Alan Apaza Aguilar
 */

public interface RentalStrategy {
    double calculateAmount(int daysRented);

    int calculateFrequentRenterPoints(int daysRented);
}
