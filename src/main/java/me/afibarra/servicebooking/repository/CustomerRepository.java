package me.afibarra.servicebooking.repository;

import me.afibarra.servicebooking.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String>,
    CustomCustomerRepository {

}
