package me.afibarra.servicebooking.service;

import me.afibarra.servicebooking.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Mono<Customer> createUser(Customer customer);

    Flux<Customer> findAll();

    Mono<Customer> changePassword(String customerId, String newPassword);
}
