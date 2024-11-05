package movies.customer.repository;

import movies.customer.Customer;

import java.util.List;

/**
 * @author Chris Alan Apaza Aguilar
 */

public interface CustomerRepository {
    List<Customer> getCustomers();
    void addCustomer(Customer customer);
    Customer getCustomerById(Long id);
}
