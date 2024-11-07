package movies.customer.service;

import lombok.extern.slf4j.Slf4j;
import movies.customer.entity.Customer;
import movies.customer.exception.CustomerException;
import movies.customer.repository.CustomerRepository;
import movies.customer.repository.CustomerRepositoryImpl;

import java.util.List;

/**
 * @author Chris Alan Apaza Aguilar
 */

@Slf4j
public class CustomerService {
    private static CustomerService instance;

    private final CustomerRepository customerRepository;

    private CustomerService() {
        this.customerRepository = CustomerRepositoryImpl.getInstance();
    }

    private CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    public static CustomerService getInstance(CustomerRepository customerRepository) {
        if (instance == null) {
            instance = new CustomerService(customerRepository);
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }

    public List<Customer> getCustomers() {
        log.info("Getting all movies");
        return customerRepository.getCustomers();
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
            throw new CustomerException("Customer not found");
        }
        return customer;
    }
}