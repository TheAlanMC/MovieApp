package movies.rental.strategy;

/**
 * @author Chris Alan Apaza Aguilar
 */

public class ChildrenRentalStrategy implements RentalStrategy {
    private static final double BASE_AMOUNT = 1.5;
    private static final int BASE_DAYS = 3;
    private static final double EXTRA_AMOUNT = 1.5;
    private static final int FREQUENT_RENTER_POINTS = 1;

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
        return FREQUENT_RENTER_POINTS;
    }
}
