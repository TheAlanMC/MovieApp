package movies.rental.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import movies.common.repository.JsonRepositoryImpl;
import movies.rental.entity.Rental;
import movies.rental.strategy.RentalStrategyFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * @author Chris Alan Apaza Aguilar
 */

public class RentalRepositoryImpl implements RentalRepository {
    private static RentalRepositoryImpl instance;
    private static final String RENTALS_FILE = "src/main/resources/rentals.json";

    private final List<Rental> rentals;
    private final JsonRepositoryImpl<Rental> jsonRepository;
    private final AtomicLong nextId;

    private RentalRepositoryImpl(JsonRepositoryImpl<Rental> jsonRepository) {
        this.jsonRepository = jsonRepository;
        this.rentals = jsonRepository.loadAll();
        this.rentals.forEach(it -> it.setStrategy(RentalStrategyFactory.createRentalStrategy(it.getRentalMovieType())));
        this.nextId = new AtomicLong(rentals.stream().mapToLong(Rental::getId).max().orElse(0) + 1);
    }

    public static RentalRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new RentalRepositoryImpl(new JsonRepositoryImpl<>(RENTALS_FILE, new TypeReference<>() {}));
        }
        return instance;
    }

    public static RentalRepositoryImpl getInstance(JsonRepositoryImpl<Rental> jsonRepository) {
        if (instance == null) {
            instance = new RentalRepositoryImpl(jsonRepository);
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }

    @Override
    public List<Rental> getRentals() {
        return rentals;
    }

    @Override
    public Rental addRental(Rental rental) {
        rental.setId(nextId.getAndIncrement());
        rentals.add(rental);
        jsonRepository.saveAll(rentals);
        return rental;
    }

    @Override
    public List<Rental> getRentalsByCustomerId(Long customerId) {
        return rentals.stream()
                .filter(rental -> rental.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }
}