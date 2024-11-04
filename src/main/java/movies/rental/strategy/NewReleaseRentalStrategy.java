package movies.rental.strategy;

import movies.rental.Rental;

/**
 * @author Chris Alan Apaza Aguilar
 */

public class NewReleaseRentalStrategy implements RentalStrategy {
    private static final double BASE_AMOUNT = 3;

    @Override
    public double calculateAmount(Rental rental) {
        return rental.getDaysRented() * BASE_AMOUNT;
    }

    @Override
    public int calculateFrequentRenterPoints(Rental rental) {
        return rental.getDaysRented() > 1 ? 2 : 1; // add bonus for a two-day new release rental
    }
}
