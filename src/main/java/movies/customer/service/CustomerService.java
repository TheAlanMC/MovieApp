package movies.customer.service;

import lombok.extern.slf4j.Slf4j;
import movies.customer.entity.Customer;
import movies.customer.repository.CustomerRepository;

import java.util.List;

/**
 * @author Chris Alan Apaza Aguilar
 */

@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addCustomer(Long id, String name) {
        log.info("Adding customer: name={}", name);
        customerRepository.addCustomer(new Customer(id, name));
        log.info("Customer added successfully: name={}", name);
    }

    public Customer getCustomerById(Long id) {
        log.info("Getting customer by ID: id={}", id);
        Customer customer = customerRepository.getCustomerById(id);
        if (customer == null) {
            log.error("Customer ID {} not found", id);
        }
        return customer;
    }

    public List<Customer> getCustomers() {
        log.info("Getting all movies");
        return customerRepository.getCustomers();
    }
}