package movies.rental.service;

import lombok.extern.slf4j.Slf4j;
import movies.customer.Customer;
import movies.customer.repository.CustomerRepository;
import movies.movie.Movie;
import movies.movie.repository.MovieRepository;
import movies.rental.Rental;
import movies.rental.exception.RentalException;
import movies.rental.repository.RentalRepository;

/**
 * @author Chris Alan Apaza Aguilar
 */

@Slf4j
public class RentalService {
    private final CustomerRepository customerRepository;
    private final MovieRepository movieRepository;
    private final RentalRepository rentalRepository;

    public RentalService(CustomerRepository customerRepository, MovieRepository movieRepository, RentalRepository rentalRepository) {
        this.customerRepository = customerRepository;
        this.movieRepository = movieRepository;
        this.rentalRepository = rentalRepository;
    }

    public void addRentalByCustomerIdAndMovieId(Long customerId, Long movieId, int daysRented) {
        Customer customer = customerRepository.getCustomerById(customerId);
        if (customer == null) {
            log.error("Customer ID {} not found", customerId);
            throw new RentalException("Customer not found: ID " + customerId);
        }

        Movie movie = movieRepository.getMovieById(movieId);
        if (movie == null) {
            log.error("Movie ID {} not found", movieId);
            throw new RentalException("Movie not found: ID " + movieId);
        }

        Rental rental = new Rental(customer, movie, daysRented);
        rentalRepository.addRental(rental);
        log.info("Rental added successfully: customerId={}, movieId={}, daysRented={}", customerId, movieId, daysRented);
    }
}

