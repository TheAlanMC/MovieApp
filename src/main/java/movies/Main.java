package movies;

import movies.customer.Customer;
import movies.customer.repository.CustomerRepositoryImpl;
import movies.movie.Movie;
import movies.movie.repository.MovieRepositoryImpl;
import movies.rental.Rental;
import movies.rental.Statement;
import movies.rental.repository.RentalRepositoryImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        MovieRepositoryImpl movieRepository = new MovieRepositoryImpl();
        RentalRepositoryImpl rentalRepository = new RentalRepositoryImpl();

        List<Customer> customers = customerRepository.getCustomers();
        List<Movie> movies = movieRepository.getMovies();

        rentalRepository.addRental(new Rental(customers.get(1), movies.get(0), 5));
        rentalRepository.addRental(new Rental(customers.get(0), movies.get(1), 1));
        rentalRepository.addRental(new Rental(customers.get(0), movies.get(2), 3));
        rentalRepository.addRental(new Rental(customers.get(1), movies.get(0), 5));
        rentalRepository.addRental(new Rental(customers.get(1), movies.get(1), 1));
        rentalRepository.addRental(new Rental(customers.get(2), movies.get(2), 3));

        System.out.println(new Statement(rentalRepository.getRentalsByCustomer(customers.get(0).getId())).generate());
        System.out.println(new Statement(rentalRepository.getRentalsByCustomer(customers.get(1).getId())).generate());
        System.out.println(new Statement(rentalRepository.getRentalsByCustomer(customers.get(2).getId())).generate());
    }
}