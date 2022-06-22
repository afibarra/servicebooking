package me.afibarra.servicebooking.model;

import me.afibarra.servicebooking.repository.CustomerRepository;
import me.afibarra.servicebooking.service.CustomerService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Mono<Customer> createUser(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Flux<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Mono<Customer> changePassword(String customerId, String newPassword) {
        return customerRepository.changePassword(customerId, newPassword);
    }
}
