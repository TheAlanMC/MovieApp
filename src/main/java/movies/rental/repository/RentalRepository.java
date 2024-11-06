package movies.rental.repository;

import movies.rental.entity.Rental;

import java.util.List;

/**
 * @author Chris Alan Apaza Aguilar
 */

public interface RentalRepository {
    List<Rental> getRentals();
    void addRental(Rental rental);
    List<Rental> getRentalsByCustomerId(Long customerId);
}
