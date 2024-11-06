package movies.rental.repository;

import movies.rental.entity.Rental;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chris Alan Apaza Aguilar
 */

public class RentalRepositoryImpl implements RentalRepository {
    private final List<Rental> rentals = new ArrayList<>();

    @Override
    public List<Rental> getRentals() {
        return new ArrayList<>(rentals);
    }

    @Override
    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    @Override
    public List<Rental> getRentalsByCustomer(Long customerId) {
        return rentals.stream()
                .filter(rental -> rental.getCustomer().getId().equals(customerId))
                .collect(Collectors.toList());
    }
}