package movies.rental;

import movies.movie.Movie;
import movies.rental.strategy.*;

public class Rental {
    private final Movie movie;
    private final int daysRented;
    private final RentalStrategy strategy;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
        this.strategy = RentalStrategyFactory.createRentalStrategy(movie.getType());
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public double calculateAmount() {
        return strategy.calculateAmount(this);
    }

    public int calculateFrequentRenterPoints() {
        return strategy.calculateFrequentRenterPoints(this);
    }
}