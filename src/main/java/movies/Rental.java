package movies;

public class Rental {
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public double calculateAmount() {
        // constants for regular rental
        final double BASE_AMOUNT_REGULAR = 2;
        final int BASE_DAYS_REGULAR = 2;
        final double EXTRA_AMOUNT_REGULAR = 1.5;
        // constants for new release rental
        final int FIXED_AMOUNT_NEW_RELEASE = 3;
        // constants for children rental
        final double BASE_AMOUNT_CHILDREN = 1.5;
        final int BASE_DAYS_CHILDREN = 3;
        final double EXTRA_AMOUNT_CHILDREN = 1.5;

        double amount = 0;
        switch (movie.getPriceCode()) {
            case REGULAR:
                amount += BASE_AMOUNT_REGULAR;
                if (daysRented > BASE_DAYS_REGULAR) {
                    amount += (daysRented - BASE_DAYS_REGULAR) * EXTRA_AMOUNT_REGULAR;
                }
                break;
            case NEW_RELEASE:
                amount += daysRented * FIXED_AMOUNT_NEW_RELEASE;
                break;
            case CHILDREN:
                amount += BASE_AMOUNT_CHILDREN;
                if (daysRented > BASE_DAYS_CHILDREN) {
                    amount += (daysRented - BASE_DAYS_CHILDREN) * EXTRA_AMOUNT_CHILDREN;
                }
                break;
            default:
                break;
        }
        return amount;
    }

    public int calculateFrequentRenterPoints() {
        // add bonus for a two-day new release rental
        if (movie.getPriceCode() == MovieType.NEW_RELEASE && daysRented > 1) {
            return 2;
        }
        // add bonus for a new release or children's rental
        if (movie.getPriceCode() == MovieType.NEW_RELEASE || movie.getPriceCode() == MovieType.CHILDREN) {
            return 1;
        }
        // no bonus for regular rental
        return 0;
    }
}