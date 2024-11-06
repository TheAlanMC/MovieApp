package movies.rental.entity;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import movies.customer.entity.Customer;
import movies.movie.entity.Movie;
import movies.rental.exception.RentalException;
import movies.rental.strategy.*;

@Getter
@Slf4j
public class Rental {
    private final Customer customer;
    private final Movie movie;
    private final int daysRented;
    private final RentalStrategy strategy;

    public Rental(Customer customer, Movie movie, int daysRented) {
        if (daysRented < 0) {
            throw new RentalException("Days rented must not be negative");
        }
        this.customer = customer;
        this.movie = movie;
        this.daysRented = daysRented;
        this.strategy = RentalStrategyFactory.createRentalStrategy(movie.getType());
    }

    public double calculateAmount() {
        return strategy.calculateAmount(daysRented);
    }

    public int calculateFrequentRenterPoints() {
        return strategy.calculateFrequentRenterPoints(daysRented);
    }
}