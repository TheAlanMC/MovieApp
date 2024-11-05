package movies.rental.strategy;

/**
 * @author Chris Alan Apaza Aguilar
 */

public class NewReleaseRentalStrategy implements RentalStrategy {
    private static final double BASE_AMOUNT = 3;

    @Override
    public double calculateAmount(int daysRented) {
        return daysRented * BASE_AMOUNT;
    }

    @Override
    public int calculateFrequentRenterPoints(int daysRented) {
        return daysRented > 1 ? 2 : 1; // add bonus for a two-day new release rental
    }
}
