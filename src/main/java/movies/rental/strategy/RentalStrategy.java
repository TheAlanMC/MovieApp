package movies.rental.strategy;

import movies.rental.Rental;

/**
 * @author Chris Alan Apaza Aguilar
 */

public interface RentalStrategy {
    double calculateAmount(Rental rental);

    int calculateFrequentRenterPoints(Rental rental);
}
