package me.afibarra.servicebooking.repository;

import me.afibarra.servicebooking.model.Customer;
import reactor.core.publisher.Mono;

public interface CustomCustomerRepository {

    Mono<Customer> changePassword(String customerId, String newPassword);
}
