package movies.rental.strategy;

import movies.rental.Rental;

/**
 * @author Chris Alan Apaza Aguilar
 */

public class ChildrenRentalStrategy implements RentalStrategy {
    private static final double BASE_AMOUNT = 1.5;
    private static final int BASE_DAYS = 3;
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
        return 1; // add bonus for children rental
    }
}
