package me.afibarra.servicebooking;

import me.afibarra.servicebooking.configurator.WebClientConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
    WebClientConfigurator.class
})
public class ServiceBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceBookingApplication.class, args);
    }
}
