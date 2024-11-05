package movies.rental.strategy;

/**
 * @author Chris Alan Apaza Aguilar
 */

public class ChildrenRentalStrategy implements RentalStrategy {
    private static final double BASE_AMOUNT = 1.5;
    private static final int BASE_DAYS = 3;
    private static final double EXTRA_AMOUNT = 1.5;

    @Override
    public double calculateAmount(int daysRented) {
        double amount = BASE_AMOUNT;
        if (daysRented > BASE_DAYS) {
            amount += (daysRented - BASE_DAYS) * EXTRA_AMOUNT;
        }
        return amount;
    }

    @Override
    public int calculateFrequentRenterPoints(int daysRented) {
        return 1; // add bonus for children rental
    }
}
