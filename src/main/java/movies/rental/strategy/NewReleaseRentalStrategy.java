package movies.rental.strategy;

/**
 * @author Chris Alan Apaza Aguilar
 */

public class NewReleaseRentalStrategy implements RentalStrategy {
    private static final double BASE_AMOUNT = 3;
    private static final int BASE_DAYS = 1;
    private static final int FREQUENT_RENTER_POINTS = 1;
    private static final int FREQUENT_RENTER_POINTS_BONUS = 1;

    @Override
    public double calculateAmount(int daysRented) {
        return daysRented * BASE_AMOUNT;
    }

    @Override
    public int calculateFrequentRenterPoints(int daysRented) {
        return daysRented > BASE_DAYS ? FREQUENT_RENTER_POINTS + FREQUENT_RENTER_POINTS_BONUS : FREQUENT_RENTER_POINTS;
    }
}
