package me.afibarra.servicebooking.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import me.afibarra.servicebooking.model.Customer;
import me.afibarra.servicebooking.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/users")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/list", produces = APPLICATION_JSON_VALUE)
    public Flux<Customer> findAll() {

        LOGGER.debug("Listing all Customers.");

        return customerService.findAll();
    }

    @PostMapping(value = "/add", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Mono<Customer> createUser(@RequestBody Customer customer) {

        LOGGER.debug("Creating a new Customer.");

        return customerService.createUser(customer);
    }

    @PostMapping(value = "/pwd", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Void>> changePassword(@RequestBody Customer customer) {

        LOGGER.debug("Changing password.");

        return customerService.changePassword(customer.getCustomerId(), customer.getPassword())
            .map(c -> ResponseEntity.accepted().build());
    }
}
