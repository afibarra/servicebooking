package me.afibarra.servicebooking.controller;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.net.URI;
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
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/users")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;
    private final WebClient webClient;

    public CustomerController(
        CustomerService customerService,
        WebClient webClient) {

        this.customerService = customerService;
        this.webClient = webClient;
    }

    @GetMapping(value = "/list", produces = APPLICATION_JSON_VALUE)
    public Flux<Customer> findAll() {

        LOGGER.debug("Listing all Customers.");

        return customerService.findAll();
    }

    @PostMapping(value = "/add", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Mono<Customer> createUser(@RequestBody Customer customer)
        throws JsonProcessingException {

        LOGGER.debug("Creating a new Customer.");

        Mono<String> response =
            webClient
                .post()
                .uri(URI.create("http://127.0.0.1:8081/v1/cache/add"))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .bodyValue("{\"customerId\":\"123-456-789\",\"firstName\":\"Armando\"}")
                .retrieve()
                .bodyToMono(String.class);

        response.subscribe(v -> LOGGER.debug("Cache response {}", v));

        return customerService.createUser(customer);
    }

    @PostMapping(value = "/pwd", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Void>> changePassword(@RequestBody Customer customer) {

        LOGGER.debug("Changing password.");

        return customerService.changePassword(customer.getCustomerId(), customer.getPassword())
            .map(c -> ResponseEntity.accepted().build());
    }
}
