package movies.rental.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import movies.common.repository.JsonRepositoryImpl;
import movies.rental.entity.Rental;
import movies.rental.strategy.RentalStrategy;
import movies.rental.strategy.RentalStrategyFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chris Alan Apaza Aguilar
 */

public class RentalRepositoryImpl implements RentalRepository {
    private static RentalRepositoryImpl instance;

    private static final String RENTALS_FILE = "src/main/resources/rentals.json";
    private final List<Rental> rentals;
    private final JsonRepositoryImpl<Rental> jsonRepository;

    private RentalRepositoryImpl() {
        this.jsonRepository = new JsonRepositoryImpl<>(RENTALS_FILE, new TypeReference<>() {});
        rentals = jsonRepository.loadAll();
        rentals.forEach(it -> it.setStrategy(RentalStrategyFactory.createRentalStrategy(it.getRentalMovieType())));
    }

    public static RentalRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new RentalRepositoryImpl();
        }
        return instance;
    }

    @Override
    public List<Rental> getRentals() {
        return rentals;
    }

    @Override
    public void addRental(Rental rental) {
        rentals.add(rental);
        jsonRepository.saveAll(rentals);
    }

    @Override
    public List<Rental> getRentalsByCustomerId(Long customerId) {
        return rentals.stream()
                .filter(rental -> rental.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }
}