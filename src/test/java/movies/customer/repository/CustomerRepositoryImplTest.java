package movies.customer.repository;

import movies.common.repository.JsonRepositoryImpl;
import movies.customer.entity.Customer;
import movies.customer.exception.CustomerException;
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

class CustomerRepositoryImplTest {

    @Mock
    private JsonRepositoryImpl<Customer> jsonRepository;

    private CustomerRepositoryImpl customerRepository;

    private List<Customer> mockCustomers;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Arrange
        mockCustomers = new ArrayList<>();
        mockCustomers.add(new Customer(1L, "Chris"));
        when(jsonRepository.loadAll()).thenReturn(mockCustomers);
        CustomerRepositoryImpl.resetInstance();
        customerRepository = CustomerRepositoryImpl.getInstance(jsonRepository);
    }

    @Test
    void getCustomersShouldReturnCustomers() {
        // Act
        List<Customer> customers = customerRepository.getCustomers();
        // Assert
        assertEquals(1, customers.size());
        assertEquals(1L, customers.get(0).getId());
        assertEquals("Chris", customers.get(0).getName());
    }

    @Test
    void addCustomerShouldAddCustomer() {
        // Arrange
        Customer customer = new Customer(2L, "Alan");
        // Act
        customerRepository.addCustomer(customer);
        // Assert
        assertEquals(2, mockCustomers.size());
        assertEquals(2L, mockCustomers.get(1).getId());
        assertEquals("Alan", mockCustomers.get(1).getName());
    }

    @Test
    void getCustomerByIdShouldReturnCustomer() {
        // Act
        Customer customer = customerRepository.getCustomerById(1L);
        // Assert
        assertEquals(1L, customer.getId());
        assertEquals("Chris", customer.getName());
    }

    @Test
    void getCustomerByIdShouldThrowCustomerException() {
        // Act
        CustomerException customerException = assertThrows(CustomerException.class, () -> customerRepository.getCustomerById(2L));
        // Assert
        assertEquals("Customer not found", customerException.getMessage());
    }
}