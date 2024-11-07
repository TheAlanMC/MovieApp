package movies.rental.service;

import lombok.extern.slf4j.Slf4j;
import movies.customer.entity.Customer;
import movies.customer.repository.CustomerRepository;
import movies.customer.repository.CustomerRepositoryImpl;
import movies.movie.entity.Movie;
import movies.movie.repository.MovieRepository;
import movies.movie.repository.MovieRepositoryImpl;
import movies.rental.entity.Rental;
import movies.rental.exception.RentalException;
import movies.rental.repository.RentalRepository;
import movies.rental.repository.RentalRepositoryImpl;

import java.util.List;

/**
 * @author Chris Alan Apaza Aguilar
 */

@Slf4j
public class RentalService {
    private static RentalService instance;

    private final CustomerRepository customerRepository;
    private final MovieRepository movieRepository;
    private final RentalRepository rentalRepository;

    private RentalService() {
        this.customerRepository = CustomerRepositoryImpl.getInstance();
        this.movieRepository = MovieRepositoryImpl.getInstance();
        this.rentalRepository = RentalRepositoryImpl.getInstance();
    }

    public static RentalService getInstance() {
        if (instance == null) {
            instance = new RentalService();
        }
        return instance;
    }

    public void addRentalByCustomerIdAndMovieId(Long id, Long customerId, Long movieId, int daysRented) {
        if (daysRented <= 0) {
            log.error("Days rented must be greater than 0");
            throw new RentalException("Days rented must be greater than 0");
        }

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

        Rental rental = new Rental(id, customer.getId(), movie.getId(), movie.getType(), daysRented);
        rentalRepository.addRental(rental);
        log.info("Rental added successfully: rentalId={}, customerId={}, movieId={}, daysRented={}",
                rental.getId(), rental.getCustomerId(), rental.getMovieId(), rental.getDaysRented());
    }

    public List<Rental> getRentalsByCustomerId(Long customerId) {
        log.info("Getting rental by customer ID: customerId={}", customerId);
        return rentalRepository.getRentalsByCustomerId(customerId);
    }
}
