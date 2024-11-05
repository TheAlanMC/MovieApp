package movies.rental.repository;

import movies.customer.Customer;
import movies.rental.Rental;

import java.util.List;

/**
 * @author Chris Alan Apaza Aguilar
 */

public interface RentalRepository {
    List<Rental> getRentals();
    void addRental(Rental rental);
    List<Rental> getRentalsByCustomer(Long customerId);
}
