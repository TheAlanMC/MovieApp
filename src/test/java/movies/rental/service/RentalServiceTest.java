package movies.rental.service;

import movies.common.type.MovieType;
import movies.customer.entity.Customer;
import movies.customer.repository.CustomerRepositoryImpl;
import movies.movie.entity.Movie;
import movies.movie.repository.MovieRepositoryImpl;
import movies.rental.entity.Rental;
import movies.rental.exception.RentalException;
import movies.rental.repository.RentalRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Chris Alan Apaza Aguilar
 */

class RentalServiceTest {

    @Mock
    private CustomerRepositoryImpl customerRepository;

    @Mock
    private MovieRepositoryImpl movieRepository;

    @Mock
    private RentalRepositoryImpl rentalRepository;

    private RentalService rentalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Arrange
        RentalService.resetInstance();
        rentalService = RentalService.getInstance(customerRepository, movieRepository, rentalRepository);
    }

    @Test
    void addRentalByCustomerIdAndMovieIdShouldAddRental() {
        // Arrange
        Customer mockCustomer = new Customer(1L, "Chris");
        Movie mockMovie = new Movie(1L, "Movie 1", MovieType.REGULAR);
        when(customerRepository.getCustomerById(1L)).thenReturn(mockCustomer);
        when(movieRepository.getMovieById(1L)).thenReturn(mockMovie);
        // Act
        rentalService.addRentalByCustomerIdAndMovieId(1L, 1L, 1L, 1);
        // Assert
        verify(rentalRepository, times(1)).addRental(any(Rental.class));
    }

    @Test
    void addRentalByCustomerIdAndMovieIdShouldThrowCustomerExceptionWhenDaysRentedIsLessThanOne() {
        // Arrange
        Customer mockCustomer = new Customer(1L, "Chris");
        Movie mockMovie = new Movie(1L, "Movie 1", MovieType.REGULAR);
        when(customerRepository.getCustomerById(1L)).thenReturn(mockCustomer);
        when(movieRepository.getMovieById(1L)).thenReturn(mockMovie);
        // Act and Assert
        RentalException rentalException = assertThrows(RentalException.class, () ->
                rentalService.addRentalByCustomerIdAndMovieId(1L, 1L, 1L, 0));
        assertEquals("Days rented must be greater than 0", rentalException.getMessage());
    }

    @Test
    void addRentalByCustomerIdAndMovieIdShouldThrowCustomerExceptionWhenCustomerIsNull() {
        // Arrange
        when(customerRepository.getCustomerById(1L)).thenReturn(null);
        // Act and Assert
        RentalException rentalException = assertThrows(RentalException.class, () ->
                rentalService.addRentalByCustomerIdAndMovieId(1L, 1L, 1L, 1));
        assertEquals("Customer not found: ID 1", rentalException.getMessage());
    }

    @Test
    void addRentalByCustomerIdAndMovieIdShouldThrowCustomerExceptionWhenMovieIsNull() {
        // Arrange
        Customer mockCustomer = new Customer(1L, "Chris");
        when(customerRepository.getCustomerById(1L)).thenReturn(mockCustomer);
        when(movieRepository.getMovieById(1L)).thenReturn(null);
        // Act and Assert
        RentalException rentalException = assertThrows(RentalException.class, () ->
                rentalService.addRentalByCustomerIdAndMovieId(1L, 1L, 1L, 1));
        assertEquals("Movie not found: ID 1", rentalException.getMessage());
    }

    @Test
    void getRentalsByCustomerIdShouldReturnRentals() {
        // Arrange
        List<Rental> mockRentals = List.of(new Rental(1L, 1L, 1L, MovieType.REGULAR, 1));
        when(rentalRepository.getRentalsByCustomerId(1L)).thenReturn(mockRentals);
        // Act
        List<Rental> rentals = rentalService.getRentalsByCustomerId(1L);
        // Assert
        verify(rentalRepository, times(1)).getRentalsByCustomerId(1L);
        assertEquals(1, rentals.size());
        assertEquals(1L, rentals.get(0).getId());
        assertEquals(1L, rentals.get(0).getCustomerId());
        assertEquals(1L, rentals.get(0).getMovieId());
        assertEquals(MovieType.REGULAR, rentals.get(0).getRentalMovieType());
        assertEquals(1, rentals.get(0).getDaysRented());
    }
}