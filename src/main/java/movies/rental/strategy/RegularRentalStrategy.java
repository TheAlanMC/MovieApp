package movies.rental.strategy;

/**
 * @author Chris Alan Apaza Aguilar
 */

import movies.rental.Rental;

public class RegularRentalStrategy implements RentalStrategy {
    private static final double BASE_AMOUNT = 2;
    private static final int BASE_DAYS = 2;
    private static final double EXTRA_AMOUNT = 1.5;

    @Override
    public double calculateAmount(Rental rental) {
        double amount = BASE_AMOUNT;
        if (rental.getDaysRented() > BASE_DAYS) {
            amount += (rental.getDaysRented() - BASE_DAYS) * EXTRA_AMOUNT;
        }
        return amount;
    }

    @Override
    public int calculateFrequentRenterPoints(Rental rental) {
        return 0; // no bonus for regular rental
    }
}
