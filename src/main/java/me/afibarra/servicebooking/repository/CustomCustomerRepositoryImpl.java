package me.afibarra.servicebooking.repository;

import me.afibarra.servicebooking.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;

public class CustomCustomerRepositoryImpl implements CustomCustomerRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    public CustomCustomerRepositoryImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<Customer> changePassword(String customerId, String newPassword) {

        Query query = new Query(Criteria.where("customerId").is(customerId));
        Update update = new Update().set("password", newPassword);

        return reactiveMongoTemplate.findAndModify(query, update, Customer.class);
    }
}
