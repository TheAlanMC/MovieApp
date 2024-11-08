package movies.customer.repository;

import movies.customer.entity.Customer;

import java.util.List;

/**
 * @author Chris Alan Apaza Aguilar
 */

public interface CustomerRepository {
    List<Customer> getCustomers();
    Customer addCustomer(Customer customer);
    Customer getCustomerById(Long id);
}
