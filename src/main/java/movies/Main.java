package movies;

import lombok.extern.slf4j.Slf4j;
import movies.common.type.MovieType;
import movies.customer.entity.Customer;
import movies.customer.service.CustomerService;
import movies.movie.entity.Movie;
import movies.movie.service.MovieService;
import movies.rental.entity.Rental;
import movies.rental.reports.Statement;
import movies.rental.service.RentalService;

import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {

        // Just for demonstration purposes
        try {
            // Create services
            CustomerService customerService = CustomerService.getInstance();
            MovieService movieService = MovieService.getInstance();
            RentalService rentalService = RentalService.getInstance();

            // Add a customer, a movie and a rental
            Customer customer = customerService.addCustomer("John Doe");
            Movie movie = movieService.addMovie("Dune", MovieType.NEW_RELEASE);
            rentalService.addRentalByCustomerIdAndMovieId(customer.getId(), movie.getId(), 3);

            // Generate statement
            List<Rental> customerRentals = rentalService.getRentalsByCustomerId(customer.getId());
            log.info("Generating statement for customer: {}", customer.getName());
            System.out.println(new Statement(customer, customerRentals).generate());
        } catch (Exception e) {
            log.error("An error occurred", e);
        }
    }
}
