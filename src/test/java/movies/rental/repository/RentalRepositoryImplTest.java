package movies.rental.repository;

import movies.common.repository.JsonRepositoryImpl;
import movies.common.type.MovieType;
import movies.rental.entity.Rental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author Chris Alan Apaza Aguilar
 */

class RentalRepositoryImplTest {

    @Mock
    private JsonRepositoryImpl<Rental> jsonRepository;

    private RentalRepositoryImpl rentalRepository;

    private List<Rental> mockRentals;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Arrange
        mockRentals = new ArrayList<>();
        mockRentals.add(new Rental(1L, 1L, 1L, MovieType.REGULAR, 1));
        mockRentals.add(new Rental(2L, 2L, 2L, MovieType.CHILDREN, 2));
        when(jsonRepository.loadAll()).thenReturn(mockRentals);
        RentalRepositoryImpl.resetInstance();
        rentalRepository = RentalRepositoryImpl.getInstance(jsonRepository);
    }

    @Test
    void getRentalsShouldReturnRentals() {
        // Act
        List<Rental> rentals = rentalRepository.getRentals();
        // Assert
        assertEquals(2, rentals.size());
        assertEquals(1L, rentals.get(0).getId());
        assertEquals(1L, rentals.get(0).getCustomerId());
        assertEquals(1L, rentals.get(0).getMovieId());
        assertEquals(MovieType.REGULAR, rentals.get(0).getRentalMovieType());
        assertEquals(1, rentals.get(0).getDaysRented());
        assertEquals(2L, rentals.get(1).getId());
        assertEquals(2L, rentals.get(1).getCustomerId());
        assertEquals(2L, rentals.get(1).getMovieId());
        assertEquals(MovieType.CHILDREN, rentals.get(1).getRentalMovieType());
        assertEquals(2, rentals.get(1).getDaysRented());
    }

    @Test
    void addRentalShouldAddRental() {
        // Arrange
        Rental rental = new Rental(3L, 3L, 3L, MovieType.NEW_RELEASE, 3);
        // Act
        Rental addedRental = rentalRepository.addRental(rental);
        // Assert
        assertEquals(3, mockRentals.size());
        assertEquals(3L, addedRental.getId());
        assertEquals(3L, addedRental.getCustomerId());
        assertEquals(3L, addedRental.getMovieId());
        assertEquals(MovieType.NEW_RELEASE, addedRental.getRentalMovieType());
        assertEquals(3, addedRental.getDaysRented());
    }

    @Test
    void getRentalsByCustomerIdShouldReturnRentals() {
        // Act
        List<Rental> rentals = rentalRepository.getRentalsByCustomerId(1L);
        // Assert
        assertEquals(1, rentals.size());
        assertEquals(1L, rentals.get(0).getId());
        assertEquals(1L, rentals.get(0).getCustomerId());
        assertEquals(1L, rentals.get(0).getMovieId());
        assertEquals(MovieType.REGULAR, rentals.get(0).getRentalMovieType());
        assertEquals(1, rentals.get(0).getDaysRented());
    }

    @Test
    void getRentalsByCustomerIdShouldReturnEmptyList() {
        // Act
        List<Rental> rentals = rentalRepository.getRentalsByCustomerId(3L);
        // Assert
        assertEquals(0, rentals.size());
    }
}